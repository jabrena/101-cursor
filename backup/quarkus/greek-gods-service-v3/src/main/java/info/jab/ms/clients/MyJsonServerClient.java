package info.jab.ms.clients;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/jabrena/latency-problems")
@RegisterRestClient(configKey="my-json-server-api")
public interface MyJsonServerClient {

    @GET
    @Path("/greek")
    @Produces(MediaType.APPLICATION_JSON)
    List<String> getExternalGreekGods();
}
