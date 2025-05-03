package info.jab.ms.repository;

import info.jab.ms.domain.GreekGod;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreekGodRepository implements PanacheRepository<GreekGod> {

    // Panache provides basic CRUD operations automatically.
    // Custom queries can be added here if needed.

    public GreekGod findByName(String name) {
        return find("name", name).firstResult();
    }
}
