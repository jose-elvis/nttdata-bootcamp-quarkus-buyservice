package nttdata.bootcamp.quarkus.buyservice.api;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.buyservice.bean.Amount;
import nttdata.bootcamp.quarkus.buyservice.dto.Wallet;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/api/wallet")
public interface WalletDigitalApi {

    @GET
    @Path("/searchCel/{nroCel}")
    public Uni<Wallet> searchWalletNroCel(@PathParam("nroCel") String documentNumber);

    @PUT
    @Path("/amount/{cellphoneNumber}")
    public Uni<Response> updateAmount(@PathParam("cellphoneNumber") String cellphoneNumber, Amount amount);

    @GET
    @Path("/searchDni/{nroDNi}")
    public Uni<Wallet> searchWalletNroDNI(@PathParam("nroDNi") String nroDNi);
}
