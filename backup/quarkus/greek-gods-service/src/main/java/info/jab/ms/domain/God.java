package info.jab.ms.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "gods")
public class God {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // JPA requires a no-arg constructor
    public God() {}

    public God(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        God god = (God) o;
        // If IDs are non-null, compare by ID. Otherwise, objects are not equal unless identical.
        return id != null && Objects.equals(id, god.id);
    }

    @Override
    public int hashCode() {
        // Use a constant if ID is null (transient state)
        return id != null ? Objects.hash(id) : System.identityHashCode(this);
    }

    @Override
    public String toString() {
        return "God{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
} 