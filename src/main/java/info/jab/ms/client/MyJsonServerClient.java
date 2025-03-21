package info.jab.ms.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class MyJsonServerClient {

    private static final Logger logger = LoggerFactory.getLogger(MyJsonServerClient.class);

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public MyJsonServerClient(
            @Value("${external.my-json-server.url}") String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl;
    }

    public List<String> getGreekGods() {
        try {
            logger.debug("Fetching Greek gods from external service: {}/greek", baseUrl);
            String[] gods = restTemplate.getForObject(baseUrl + "/greek", String[].class);
            return gods != null ? Arrays.asList(gods) : Collections.emptyList();
        } catch (Exception e) {
            logger.error("Error fetching Greek gods from external service", e);
            return Collections.emptyList();
        }
    }
} 