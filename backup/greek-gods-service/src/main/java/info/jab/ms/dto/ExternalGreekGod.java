package info.jab.ms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalGreekGod {

    public Long id;
    public String name;

    // Default constructor for JSON deserialization
    public ExternalGreekGod() {
    }

    public ExternalGreekGod(String name) {
        this.id = null; // Assuming id is not provided in this scenario
        this.name = name;
    }

    public ExternalGreekGod(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExternalGreekGod that = (ExternalGreekGod) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ExternalGreekGod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
} 