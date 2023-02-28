package nttdata.bootcamp.quarkus.buyservice;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.buyservice.api.BootcoinDigitalApi;
import nttdata.bootcamp.quarkus.buyservice.api.WalletDigitalApi;
import nttdata.bootcamp.quarkus.buyservice.bean.Amount;
import nttdata.bootcamp.quarkus.buyservice.dto.Bootcoin;
import nttdata.bootcamp.quarkus.buyservice.dto.BuyServiceResponse;
import nttdata.bootcamp.quarkus.buyservice.dto.Wallet;
import nttdata.bootcamp.quarkus.buyservice.entity.BuyServiceEntity;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/buyservicio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class BuyServiceResource {

    @Inject
    @RestClient
    private WalletDigitalApi walletDigitalApi;

    @Inject
    @RestClient
    private BootcoinDigitalApi bootcoinDigitalApi;

    @GET
    public Multi<BuyServiceEntity> listAll() {
        return BuyServiceEntity.streamAll();
    }
    @POST
    public Uni<BuyServiceResponse> saveBuyService(BuyServiceEntity buyServiceEntity){

        BuyServiceResponse response = new BuyServiceResponse();
        Uni<Wallet> walletClientEnvia = walletDigitalApi.searchWalletNroDNI(buyServiceEntity.getDniComprador());
        Uni<Bootcoin> bootcoinUni = bootcoinDigitalApi.searchClienteByDocumentNumber(buyServiceEntity.getDniComprador());

        return walletClientEnvia.flatMap(walletenvia -> {

            return  bootcoinUni.flatMap(bootcoin -> {

                double totalCambio = buyServiceEntity.getAmount() * buyServiceEntity.getPriceBootcoin();
                System.out.println("totalCambio :" +totalCambio);
                if(walletenvia == null){
                    response.setCodigoRespuesta(1);
                    response.setMensajeRespuesta("Dni: "+buyServiceEntity.getDniComprador()+" no registrado en Wallet Digital");
                    return Uni.createFrom().item(response);
                } else if (bootcoin == null) {
                    response.setCodigoRespuesta(1);
                    response.setMensajeRespuesta("Dni: "+buyServiceEntity.getDniComprador()+" no registrado en Wallet Bootcoin");
                    return Uni.createFrom().item(response);
                } else if (walletenvia.getAmount() < totalCambio) {
                    response.setCodigoRespuesta(2);
                    response.setMensajeRespuesta("Saldo Insuficiente");
                    return Uni.createFrom().item(response);
                }else if (walletenvia.getAmount() > totalCambio){

                    Amount amount = new Amount();
                    Amount nuevoSaldoBootcoin = new Amount();
                    double nuevoSaldoEnvia;
                    nuevoSaldoEnvia = walletenvia.getAmount() - totalCambio;
                    amount.setAmount(nuevoSaldoEnvia);
                    nuevoSaldoBootcoin.setAmount(buyServiceEntity.getAmount());
                    System.out.println("Nuevo Saldo :" + amount.getAmount());
                    return walletDigitalApi.updateAmount(walletenvia.getCellphoneNumber(), amount).onItem().transformToUni((response1) -> {
                        return bootcoinDigitalApi.updateAmount(walletenvia.getCellphoneNumber(), nuevoSaldoBootcoin).onItem().transformToUni((response2) -> {
                            response.setCodigoRespuesta(0);
                            response.setMensajeRespuesta("Registro Exitoso");
                            return buyServiceEntity.persist().
                                    map(r -> response);
                        });

                    });
                }
                return null;
            });
        });
    }
}
