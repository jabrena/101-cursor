package info.jab.ms.services;

import info.jab.ms.domain.God;
import info.jab.ms.repository.GodRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsible for querying God data from the database.
 */
@ApplicationScoped
public class GodQueryService {

    private static final Logger logger = LoggerFactory.getLogger(GodQueryService.class);

    // Dependency will be injected (Task 4.6)
    @Inject
    GodRepository godRepository;

    // Method to retrieve all god names (Task 4.7)
    public List<String> getAllGodNames() {
        logger.info("Retrieving all god names from the database.");
        List<God> gods = godRepository.listAll();
        List<String> godNames = gods.stream()
                                     .map(God::getName)
                                     .collect(Collectors.toList());
        logger.info("Retrieved {} god names.", godNames.size());
        return godNames;
    }

    public GodQueryService() {
        // Constructor logic if needed
        logger.info("GodQueryService initialized");
    }

    // Other methods if needed
} 