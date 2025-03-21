package info.jab.ms.service;

import info.jab.ms.client.MyJsonServerClient;
import info.jab.ms.repository.GreekGod;
import info.jab.ms.repository.GreekGodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GodUpdaterService {

    private static final Logger logger = LoggerFactory.getLogger(GodUpdaterService.class);

    private final MyJsonServerClient myJsonServerClient;
    private final GreekGodRepository greekGodRepository;

    public GodUpdaterService(
            MyJsonServerClient myJsonServerClient,
            GreekGodRepository greekGodRepository) {
        this.myJsonServerClient = myJsonServerClient;
        this.greekGodRepository = greekGodRepository;
    }

    @Scheduled(fixedRateString = "${external.my-json-server.update-interval-seconds:10}000")
    public void updateGreekGods() {
        logger.info("Starting scheduled update of Greek gods from external service");
        
        List<String> godNames = myJsonServerClient.getGreekGods();
        
        if (!godNames.isEmpty()) {
            logger.debug("Retrieved {} Greek gods from external service", godNames.size());
            
            List<GreekGod> godsToSave = godNames.stream()
                    .map(GreekGod::new)
                    .collect(Collectors.toList());
            
            // Simple approach: clear and recreate
            try {
                greekGodRepository.deleteAll();
                greekGodRepository.saveAll(godsToSave);
                logger.info("Successfully updated {} Greek gods in the database", godsToSave.size());
            } catch (Exception e) {
                logger.error("Error updating Greek gods in the database", e);
            }
        } else {
            logger.warn("No Greek gods retrieved from external service, skipping database update");
        }
    }
} 