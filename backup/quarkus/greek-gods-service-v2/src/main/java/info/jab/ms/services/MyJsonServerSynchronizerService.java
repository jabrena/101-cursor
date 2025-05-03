package info.jab.ms.services;

import info.jab.ms.domain.GreekGod;
import info.jab.ms.repository.GreekGodRepository;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class MyJsonServerSynchronizerService {

    private static final Logger log = LoggerFactory.getLogger(MyJsonServerSynchronizerService.class);

    @Inject
    @RestClient
    MyJsonServerClient myJsonServerClient;

    @Inject
    GreekGodRepository repository;

    // Dependencies (REST Client, Repository) will be injected in task 3.3

    // Scheduled synchronization logic will be implemented in task 3.4 - 3.7

    @Scheduled(every = "10s")
    @Transactional
    void synchronizeGods() {
        log.info("Starting external Greek gods synchronization...");
        try {
            // Logic to call external API (task 3.5)
            List<ExternalGreekGod> externalGods = myJsonServerClient.getExternalGreekGods();
            log.info("Fetched {} gods from external source", externalGods.size());

            // Logic to update database (task 3.6)
            int newGodsCount = 0;
            for (ExternalGreekGod externalGod : externalGods) {
                // Check if god with this name already exists (case-sensitive check)
                if (repository.find("name", externalGod.name).firstResult() == null) {
                    GreekGod newGod = new GreekGod();
                    newGod.name = externalGod.name;
                    repository.persist(newGod);
                    newGodsCount++;
                } else {
                    log.debug("Skipping existing god: {}", externalGod.name);
                }
            }
            log.info("Synchronization finished. Added {} new gods.", newGodsCount);

        } catch (Exception e) {
            // Error handling (task 3.7) - Log error if external service fails or DB issue occurs
            log.error("Error during external gods synchronization: {}", e.getMessage(), e);
            // Note: Transaction might be rolled back automatically depending on the exception
            // and Quarkus transaction configuration.
        }
    }
} 