package info.jab.ms.services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/greek") // Base path from the server URL in application.properties + this path
@RegisterRestClient(configKey="my-json-server-api") // Config key to link to application.properties
public interface MyJsonServerClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ExternalGreekGod> getExternalGreekGods();
} 