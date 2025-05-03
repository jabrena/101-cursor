package info.jab.ms.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO representing the structure of a God object from the external API.
 */
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore fields not defined here
public class ExternalGod {

    private String name;

    public ExternalGod() {
    }

    public ExternalGod(String name) {
        this.name = name;
    }

    // Getters and setters are needed for Jackson deserialization
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Optional: toString for logging/debugging
    @Override
    public String toString() {
        return "ExternalGod{" +
               "name='" + name + '\'' +
               '}';
    }
}