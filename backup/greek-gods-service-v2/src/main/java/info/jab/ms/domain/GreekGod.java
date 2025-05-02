package info.jab.ms.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "greek_god")
public class GreekGod extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String name;

    // PanacheEntity provides the 'id' field (Long)
    
    @Override
    public String toString() {
        return name;
    }
} 