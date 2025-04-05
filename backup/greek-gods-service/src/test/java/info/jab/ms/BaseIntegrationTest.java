package info.jab.ms;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

public class BaseIntegrationTest implements QuarkusTestResourceLifecycleManager {

    private static PostgreSQLContainer<?> postgresContainer;
    private static WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        // Start PostgreSQL
        postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16"))
                .withDatabaseName("greekgods")
                .withUsername("postgres")
                .withPassword("postgres");
        postgresContainer.start();

        // Start WireMock
        wireMockServer = new WireMockServer(8089); // Use a different port than the application
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());

        // Configure Quarkus properties for the test
        Map<String, String> config = new HashMap<>();
        config.put("quarkus.datasource.jdbc.url", postgresContainer.getJdbcUrl());
        config.put("quarkus.datasource.username", postgresContainer.getUsername());
        config.put("quarkus.datasource.password", postgresContainer.getPassword());
        config.put("quarkus.hibernate-orm.database.generation", "drop-and-create"); // Create schema for tests
        config.put("quarkus.rest-client.my-json-server-api.url", wireMockServer.baseUrl()); // Point client to WireMock

        return config;
    }

    @Override
    public void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
            wireMockServer = null;
        }
        if (postgresContainer != null) {
            postgresContainer.stop();
            postgresContainer = null;
        }
    }

    public static WireMockServer getWireMockServer() {
        return wireMockServer;
    }
} 