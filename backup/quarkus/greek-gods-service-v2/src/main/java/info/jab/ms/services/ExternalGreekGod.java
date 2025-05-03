package info.jab.ms.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Simple DTO for the external API response
// Ignore unknown properties to avoid errors if the external API adds fields
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalGreekGod {
    public Long id;
    public String name;

    // Default constructor for JPA
    public ExternalGreekGod() {
    }

    public ExternalGreekGod(String name) {
        this.name = name;
    }
}
 