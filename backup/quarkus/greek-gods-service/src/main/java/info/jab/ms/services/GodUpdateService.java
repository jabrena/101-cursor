package info.jab.ms.services;

import info.jab.ms.domain.God;
import info.jab.ms.repository.GodRepository;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsible for updating the database with data
 * fetched from the external God API.
 */
@ApplicationScoped
public class GodUpdateService {

    private static final Logger logger = LoggerFactory.getLogger(GodUpdateService.class);

    // Dependencies will be injected (Task 4.3)
    @Inject
    @RestClient
    ExternalGodClient externalGodClient;

    @Inject
    GodRepository godRepository;

    // Scheduled method will be added (Task 4.2 & 4.4)
    @Scheduled(every = "10s", identity = "update-god-job")
    @Transactional
    void updateGodDatabase() {
        logger.info("Scheduled task: updateGodDatabase running...");

        try {
            List<ExternalGod> externalGods = externalGodClient.getGods();
            logger.info("Retrieved {} gods from external API.", externalGods.size());

            // Clear existing gods
            long deletedCount = godRepository.deleteAll();
            logger.info("Deleted {} existing gods from the database.", deletedCount);

            // Create and persist new God entities
            List<God> newGods = externalGods.stream()
                .map(externalGod -> new God(externalGod.getName())) // Map DTO to Entity
                .collect(Collectors.toList());

            godRepository.persist(newGods);
            logger.info("Persisted {} new gods to the database.", newGods.size());

        } catch (WebApplicationException e) {
            // Log REST client errors (e.g., connection issues, 4xx/5xx responses)
            logger.error("Error calling external God API: Status {}, Response: {}",
                         e.getResponse() != null ? e.getResponse().getStatus() : "N/A",
                         e.getResponse() != null ? e.getResponse().readEntity(String.class) : e.getMessage(), e);
        } catch (Exception e) {
            // Catch other potential exceptions during the process
            logger.error("An unexpected error occurred during the god update task: {}", e.getMessage(), e);
        }
    }

    public GodUpdateService() {
        // Constructor logic if needed
        logger.info("GodUpdateService initialized");
    }

    // Other methods if needed
} 