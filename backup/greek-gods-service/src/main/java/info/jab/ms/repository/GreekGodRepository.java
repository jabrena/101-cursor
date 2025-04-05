package info.jab.ms.repository;

import info.jab.ms.model.GreekGod;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class GreekGodRepository implements PanacheRepository<GreekGod> {

    public Optional<GreekGod> findByName(String name) {
        return find("name", name).firstResultOptional();
    }
} 