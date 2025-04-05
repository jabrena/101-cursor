package info.jab.ms.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "greek_god")
public class GreekGod extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true, length = 100)
    public String name;

    // Default constructor for JPA
    public GreekGod() {
    }

    public GreekGod(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GreekGod greekGod = (GreekGod) o;
        return Objects.equals(id, greekGod.id) && Objects.equals(name, greekGod.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "GreekGod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
} 