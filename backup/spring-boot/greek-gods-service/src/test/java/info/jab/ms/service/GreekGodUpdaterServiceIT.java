package info.jab.ms.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import info.jab.ms.domain.entity.GreekGod;
import info.jab.ms.repository.GreekGodRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.stream.StreamSupport;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = {GreekGodUpdaterServiceIT.Initializer.class})
class GreekGodUpdaterServiceIT {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("test-greekgods")
            .withUsername("test")
            .withPassword("test");

    private static final WireMockServer wireMockServer = new WireMockServer(0);

    @Autowired
    private GreekGodUpdaterService updaterService;

    @Autowired
    private GreekGodRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());

        stubFor(get(urlEqualTo("/greek"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[\"Zeus\", \"Hera\", \"Poseidon\"]")));
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
        wireMockServer.stop();
    }

    @Test
    void updateGreekGodsDatabase_ShouldAddNewGods() {
        // Given
        assertTrue(repository.findAll().iterator().hasNext() == false, "Database should be empty initially");

        // When
        updaterService.updateGreekGodsDatabase();

        // Then
        List<GreekGod> gods = StreamSupport.stream(repository.findAll().spliterator(), false)
                .toList();

        assertEquals(3, gods.size(), "Should have 3 Greek gods in the database");
        assertTrue(gods.stream().anyMatch(god -> "Zeus".equals(god.getName())), "Zeus should be in the database");
        assertTrue(gods.stream().anyMatch(god -> "Hera".equals(god.getName())), "Hera should be in the database");
        assertTrue(gods.stream().anyMatch(god -> "Poseidon".equals(god.getName())), "Poseidon should be in the database");

        // Verify that the external API was called
        verify(getRequestedFor(urlEqualTo("/greek")));
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext context) {
            wireMockServer.start();
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "external.api.url=http://localhost:" + wireMockServer.port()
            ).applyTo(context.getEnvironment());
        }
    }
}