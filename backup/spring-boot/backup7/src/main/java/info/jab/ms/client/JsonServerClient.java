package info.jab.ms.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class JsonServerClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public JsonServerClient(
            @Value("${external.api.url}") String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl;
    }

    public List<String> getGreekGods() {
        ResponseEntity<List<String>> response = restTemplate.exchange(
                baseUrl + "/greek",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {}
        );
        return response.getBody();
    }
} 