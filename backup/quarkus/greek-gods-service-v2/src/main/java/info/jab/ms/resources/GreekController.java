package info.jab.ms.resources;

import info.jab.ms.domain.GreekGod;
import info.jab.ms.services.GreekGodService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/api/v1/gods")
public class GreekController {

    private static final Logger log = LoggerFactory.getLogger(GreekController.class);

    @Inject
    GreekGodService service;

    @GET
    @Path("/greek")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GreekGod> getGreekGods() {
        log.info("Received request to get all Greek gods");
        // Actual implementation calling the service in task 2.4
        // Returning empty list for now to fulfill the contract
        return service.getAllGods();
    }
} 