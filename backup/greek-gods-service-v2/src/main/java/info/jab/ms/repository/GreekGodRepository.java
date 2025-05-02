package info.jab.ms.repository;

import info.jab.ms.domain.GreekGod;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreekGodRepository implements PanacheRepository<GreekGod> {

    // PanacheRepository provides standard CRUD operations
    // Add custom query methods here if needed
} 