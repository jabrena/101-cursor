package info.jab.ms.service;

import info.jab.ms.model.GreekGod;
import info.jab.ms.repository.GreekGodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GreekGodServiceImpl implements GreekGodService {

    private static final Logger logger = LoggerFactory.getLogger(GreekGodServiceImpl.class);
    
    private final GreekGodRepository greekGodRepository;

    public GreekGodServiceImpl(GreekGodRepository greekGodRepository) {
        this.greekGodRepository = greekGodRepository;
    }

    @Override
    public List<GreekGod> getAllGods() {
        logger.debug("Getting all Greek gods from database");
        List<info.jab.ms.repository.GreekGod> entities = greekGodRepository.findAll();
        
        return entities.stream()
                .map(this::mapToApiModel)
                .collect(Collectors.toList());
    }
    
    private GreekGod mapToApiModel(info.jab.ms.repository.GreekGod entity) {
        GreekGod model = new GreekGod();
        model.setId(entity.getId());
        model.setName(entity.getName());
        return model;
    }
} 