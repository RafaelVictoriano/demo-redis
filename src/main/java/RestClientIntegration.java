import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Map;

@Path("/extensions")
@RegisterRestClient(configKey = "extension")
public interface RestClientIntegration {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
     Map<String, String> hello();
}

