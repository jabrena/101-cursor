package info.jab.ms.integration;

import info.jab.ms.model.GreekGod;
import info.jab.ms.repository.GreekGodRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class GreekGodsApiIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GreekGodRepository greekGodRepository;

    @BeforeEach
    void setUp() {
        // Add some test data
        greekGodRepository.save(new info.jab.ms.repository.GreekGod("Zeus"));
        greekGodRepository.save(new info.jab.ms.repository.GreekGod("Poseidon"));
        greekGodRepository.save(new info.jab.ms.repository.GreekGod("Hades"));
    }

    @AfterEach
    void tearDown() {
        greekGodRepository.deleteAll();
    }

    @Test
    void getAllGreekGods_ReturnsGreekGods() {
        // Construct the URL
        String url = "http://localhost:" + port + "/api/v1/gods/greek";

        // Make the request
        ResponseEntity<List<GreekGod>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GreekGod>>() {}
        );

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<GreekGod> gods = response.getBody();
        assertNotNull(gods);
        assertEquals(3, gods.size());
    }
} 