package info.jab.ms.services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

/**
 * REST Client interface for the external Greek Gods API.
 * Configuration for the base URL is expected under the 'external.god.api' prefix
 * in application.properties (e.g., external.god.api/mp-rest/url).
 */
@RegisterRestClient(configKey = "external.god.api")
@Path("/gods") // Assuming the endpoint path is /gods based on convention
public interface ExternalGodClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ExternalGod> getGods();

} 