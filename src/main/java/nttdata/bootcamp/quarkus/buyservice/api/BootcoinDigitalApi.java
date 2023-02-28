package nttdata.bootcamp.quarkus.buyservice.api;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.buyservice.bean.Amount;
import nttdata.bootcamp.quarkus.buyservice.dto.Bootcoin;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/api/bootcoin")
public interface BootcoinDigitalApi {
    @GET
    @Path("/search/{dniClient}")
    public Uni<Bootcoin> searchClienteByDocumentNumber(@PathParam("dniClient") String dniClient);

    @PUT
    @Path("/amount/{cellphoneNumber}")
    public Uni<Response> updateAmount(@PathParam("cellphoneNumber") String cellphoneNumber, Amount amount);
}
