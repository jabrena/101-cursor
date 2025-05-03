package info.jab.ms.repository;

import info.jab.ms.domain.God;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repository for the God entity using Panache.
 */
@ApplicationScoped
public class GodRepository implements PanacheRepository<God> {

    // Panache provides standard CRUD methods automatically.
    // Custom query methods can be added here if needed.

} 