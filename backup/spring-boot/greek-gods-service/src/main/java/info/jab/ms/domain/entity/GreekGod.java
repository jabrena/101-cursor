package info.jab.ms.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("greek_god")
public class GreekGod {

    @Id
    private Long id;
    private String name;

    public GreekGod() {
    }

    public GreekGod(String name) {
        this.name = name;
    }

    public GreekGod(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "GreekGod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}