package info.jab.ms.service;

import info.jab.ms.client.MyJsonServerClient;
import info.jab.ms.dto.ExternalGreekGod;
import info.jab.ms.model.GreekGod;
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

    @Scheduled(every = "10s")
    @Transactional
    void synchronizeGods() {
        log.info("Starting synchronization with my-json-server");
        try {
            List<ExternalGreekGod> externalGods = myJsonServerClient.getExternalGreekGods();
            log.info("Received {} gods from external service", externalGods.size());

            for (ExternalGreekGod externalGod : externalGods) {
                repository.findByName(externalGod.name).ifPresentOrElse(
                    existingGod -> log.debug("God '{}' already exists, skipping.", existingGod.name),
                    () -> {
                        GreekGod newGod = new GreekGod(externalGod.name);
                        repository.persist(newGod);
                        log.info("Persisted new god: {}", newGod.name);
                    }
                );
            }
            log.info("Synchronization finished successfully.");
        } catch (Exception e) {
            log.error("Error during synchronization: {}", e.getMessage(), e);
        }
    }
} 