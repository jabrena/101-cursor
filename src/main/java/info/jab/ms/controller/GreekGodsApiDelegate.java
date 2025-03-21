package info.jab.ms.controller;

import info.jab.ms.model.GreekGod;
import info.jab.ms.service.GreekGodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GreekGodsApiDelegate implements GodsApi {

    private static final Logger logger = LoggerFactory.getLogger(GreekGodsApiDelegate.class);
    
    private final GreekGodService greekGodService;

    public GreekGodsApiDelegate(GreekGodService greekGodService) {
        this.greekGodService = greekGodService;
    }

    @Override
    public ResponseEntity<List<GreekGod>> getAllGreekGods() {
        logger.info("Processing request to get all Greek gods");
        List<GreekGod> gods = greekGodService.getAllGods();
        logger.info("Returning {} Greek gods", gods.size());
        return ResponseEntity.ok(gods);
    }
} 