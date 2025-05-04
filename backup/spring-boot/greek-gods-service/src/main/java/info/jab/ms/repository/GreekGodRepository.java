package info.jab.ms.repository;

import info.jab.ms.domain.entity.GreekGod;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GreekGodRepository extends CrudRepository<GreekGod, Long> {
    Optional<GreekGod> findByName(String name);
}