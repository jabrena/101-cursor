package info.jab.ms.service;

import info.jab.ms.model.GreekGod;
import info.jab.ms.repository.GreekGodRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class GreekGodService {

    private static final Logger log = LoggerFactory.getLogger(GreekGodService.class);

    @Inject
    GreekGodRepository repository;

    public List<GreekGod> getAllGods() {
        log.info("Retrieving all Greek gods");
        List<GreekGod> gods = repository.listAll();
        log.info("Found {} Greek gods", gods.size());
        return gods;
    }
} 