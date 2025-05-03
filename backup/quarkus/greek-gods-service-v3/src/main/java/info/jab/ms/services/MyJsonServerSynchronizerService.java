package info.jab.ms.services;

import info.jab.ms.clients.MyJsonServerClient;
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
import java.util.stream.Collectors;

@ApplicationScoped
public class MyJsonServerSynchronizerService {

    private static final Logger log = LoggerFactory.getLogger(MyJsonServerSynchronizerService.class);

    @Inject
    @RestClient
    MyJsonServerClient myJsonServerClient;

    @Inject
    GreekGodRepository greekGodRepository;

    @Scheduled(every = "10s", identity = "sync-external-gods")
    @Transactional
    void synchronizeGreekGods() {
        log.info("Starting synchronization with external Greek Gods API");
        try {
            List<String> externalGodNames = myJsonServerClient.getExternalGreekGods();
            log.info("Fetched {} god names from external API", externalGodNames.size());

            List<GreekGod> godsToPersist = externalGodNames.stream()
                .map(godName -> {
                    // Try to find existing god by name to update, otherwise create new
                    GreekGod existingGod = greekGodRepository.findByName(godName);
                    if (existingGod != null) {
                        log.debug("God already exists: {}", existingGod.getName());
                        return existingGod;
                    } else {
                        log.debug("Creating new god: {}", godName);
                        return new GreekGod(godName);
                    }
                })
                .distinct() // Ensure we don't try to persist duplicates from the source list
                .collect(Collectors.toList());

            // Persist new or merge existing entities
            // Panache's persist method handles both creation and merging detached entities
            if (!godsToPersist.isEmpty()) {
                greekGodRepository.persist(godsToPersist);
                log.info("Successfully synchronized {} gods into the database", godsToPersist.size());
            } else {
                log.info("No new or updated gods to persist.");
            }

        } catch (Exception e) {
            log.error("Error during synchronization with external Greek Gods API: {}", e.getMessage(), e);
        }
    }
}
