package info.jab.ms.client;

import info.jab.ms.dto.ExternalGreekGod;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "my-json-server-api")
@Path("/greek")
public interface MyJsonServerClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ExternalGreekGod> getExternalGreekGods();
} 