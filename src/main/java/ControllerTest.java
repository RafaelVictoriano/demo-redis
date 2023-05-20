import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/ola")
public class ControllerTest {

    @Inject
    ClientIngration clientIngration;

    @GET
    public Response ola(@QueryParam("cache") String cache)  {
        return Response.ok(clientIngration.get(cache)).build();
    }

}
