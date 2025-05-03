package info.jab.ms.services;

import info.jab.ms.domain.GreekGod;
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
        log.info("Retrieving all Greek gods from repository");
        List<GreekGod> gods = repository.listAll();
        log.info("Found {} gods", gods.size());
        return gods;
    }
} 