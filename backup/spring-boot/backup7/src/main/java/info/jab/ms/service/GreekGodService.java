package info.jab.ms.service;

import info.jab.ms.domain.entity.GreekGod;
import info.jab.ms.repository.GreekGodRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GreekGodService {

    private final GreekGodRepository repository;

    public GreekGodService(GreekGodRepository repository) {
        this.repository = repository;
    }

    public List<GreekGod> getAllGods() {
        List<GreekGod> gods = new ArrayList<>();
        repository.findAll().forEach(gods::add);
        return gods;
    }
} 