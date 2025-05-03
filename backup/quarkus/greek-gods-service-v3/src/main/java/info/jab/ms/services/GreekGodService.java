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
    GreekGodRepository greekGodRepository;

    public List<GreekGod> getAllGreekGods() {
        log.info("Fetching all Greek gods from the database");
        List<GreekGod> gods = greekGodRepository.listAll();
        log.info("Found {} Greek gods", gods.size());
        return gods;
    }
}
