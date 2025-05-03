package info.jab.ms.service;

import info.jab.ms.client.JsonServerClient;
import info.jab.ms.domain.entity.GreekGod;
import info.jab.ms.repository.GreekGodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreekGodUpdaterService {

    private static final Logger logger = LoggerFactory.getLogger(GreekGodUpdaterService.class);

    private final JsonServerClient jsonServerClient;
    private final GreekGodRepository repository;

    public GreekGodUpdaterService(JsonServerClient jsonServerClient, GreekGodRepository repository) {
        this.jsonServerClient = jsonServerClient;
        this.repository = repository;
    }

    @Scheduled(fixedRateString = "${external.api.scheduler.rate}")
    public void updateGreekGodsDatabase() {
        logger.info("Starting scheduled update of Greek gods database");
        try {
            List<String> godNames = jsonServerClient.getGreekGods();
            logger.info("Retrieved {} Greek gods from external API", godNames.size());

            for (String name : godNames) {
                Optional<GreekGod> existingGod = repository.findByName(name);
                if (existingGod.isEmpty()) {
                    GreekGod newGod = new GreekGod(name);
                    repository.save(newGod);
                    logger.info("Added new Greek god: {}", name);
                }
            }
            logger.info("Database update completed successfully");
        } catch (Exception e) {
            logger.error("Error updating Greek gods database", e);
        }
    }
}