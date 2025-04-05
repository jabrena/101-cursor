package info.jab.ms.controller;

import info.jab.ms.model.GreekGod;
import info.jab.ms.service.GreekGodService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/api/v1/gods/greek")
@Produces(MediaType.APPLICATION_JSON)
public class GreekController {

    private static final Logger log = LoggerFactory.getLogger(GreekController.class);

    @Inject
    GreekGodService service;

    @GET
    public List<GreekGod> getGreekGods() {
        log.info("Received request to get all Greek gods");
        return service.getAllGods();
    }
} 