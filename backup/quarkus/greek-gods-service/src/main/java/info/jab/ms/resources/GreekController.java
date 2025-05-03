package info.jab.ms.resources;

import info.jab.ms.services.GodQueryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * REST controller for accessing Greek God information.
 */
@Path("/api/v1/gods")
@Produces(MediaType.APPLICATION_JSON)
public class GreekController {

    private static final Logger logger = LoggerFactory.getLogger(GreekController.class);

    // Dependency will be injected (Task 5.2)
    @Inject
    GodQueryService godQueryService;

    // GET endpoint will be implemented (Task 5.3)
    @GET
    public List<String> getGods() {
        logger.info("GET /gods endpoint called");
        List<String> godNames = godQueryService.getAllGodNames();
        logger.info("Returning {} god names.", godNames.size());
        return godNames;
    }

    public GreekController() {
        // Constructor logic if needed
        logger.info("GreekController initialized");
    }
}