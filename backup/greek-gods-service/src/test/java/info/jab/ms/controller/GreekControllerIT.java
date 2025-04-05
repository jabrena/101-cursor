package info.jab.ms.controller;

import info.jab.ms.BaseIntegrationTest;
import info.jab.ms.model.GreekGod;
import info.jab.ms.repository.GreekGodRepository;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.time.Duration;

import static org.awaitility.Awaitility.await;


@QuarkusTest
@QuarkusTestResource(BaseIntegrationTest.class)
public class GreekControllerIT {

    private static final Logger log = LoggerFactory.getLogger(GreekControllerIT.class);

    @Inject
    GreekGodRepository repository;

    @BeforeEach
    @Transactional
    void setup() {
        log.info("Cleaning up database before test");
        repository.deleteAll(); // Clean DB before each test
        log.info("Database cleanup complete");

        log.info("Resetting WireMock state");
        BaseIntegrationTest.getWireMockServer().resetAll(); // Reset WireMock before each test
        log.info("WireMock state reset");
    }

    @Test
    public void testGetGreekGodsEndpoint_InitiallyEmpty() {
        log.info("Testing GET /api/v1/gods/greek when database is empty");
        given()
            .when().get("/api/v1/gods/greek")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("$", hasSize(0));
        log.info("Test GET /api/v1/gods/greek (empty DB) successful");
    }

    @Test
    public void testGetGreekGodsEndpoint_AfterSynchronization() {
        log.info("Testing GET /api/v1/gods/greek after synchronization");

        // Mock the external API response
        log.info("Setting up WireMock stub for external API");
        BaseIntegrationTest.getWireMockServer().stubFor(get(urlEqualTo("/greek"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("[{\"id\": 1, \"name\": \"Zeus\"}, {\"id\": 2, \"name\": \"Hera\"}]")));
        log.info("WireMock stub configured");

        // Wait for the scheduler to run (at least once, allow up to 15 seconds for safety)
        log.info("Waiting for scheduler to run and populate database...");
        await().atMost(Duration.ofSeconds(15)).untilAsserted(() -> {
            log.debug("Checking endpoint for expected size...");
            given()
                .when().get("/api/v1/gods/greek")
                .then()
                .statusCode(200)
                .body("$", hasSize(2));
        });
        log.info("Database populated by scheduler");

        // Test the endpoint
        log.info("Performing GET request to /api/v1/gods/greek");
        given()
            .when().get("/api/v1/gods/greek")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("$", hasSize(2))
            .body("[0].name", equalTo("Zeus"))
            .body("[1].name", equalTo("Hera"));
        log.info("Test GET /api/v1/gods/greek (after sync) successful");
    }

    @Test
    public void testSynchronization_HandlesDuplicates() {
        log.info("Testing synchronization handles duplicates");

        // Mock the external API response (first call)
        log.info("Setting up WireMock stub (call 1)");
        BaseIntegrationTest.getWireMockServer().stubFor(get(urlEqualTo("/greek"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("[{\"id\": 1, \"name\": \"Zeus\"}]")));
        log.info("WireMock stub (call 1) configured");

        // Wait for the first synchronization
        log.info("Waiting for first synchronization...");
        await().atMost(Duration.ofSeconds(15)).untilAsserted(() -> {
            log.debug("Checking endpoint for size 1...");
             given()
                .when().get("/api/v1/gods/greek")
                .then()
                .statusCode(200)
                .body("$", hasSize(1));
        });
        log.info("First synchronization complete");

        // Verify initial state
        given()
            .when().get("/api/v1/gods/greek")
            .then()
            .statusCode(200)
            .body("$", hasSize(1))
            .body("[0].name", equalTo("Zeus"));

        // Mock the external API response (second call with duplicate)
        log.info("Setting up WireMock stub (call 2 - with duplicate)");
        BaseIntegrationTest.getWireMockServer().resetAll(); // Reset to ensure the new stub is used
        BaseIntegrationTest.getWireMockServer().stubFor(get(urlEqualTo("/greek"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("[{\"id\": 1, \"name\": \"Zeus\"}, {\"id\": 2, \"name\": \"Hera\"}]")));
         log.info("WireMock stub (call 2) configured");

        // Wait for the second synchronization
        log.info("Waiting for second synchronization...");
        await().atMost(Duration.ofSeconds(15)).untilAsserted(() -> {
            log.debug("Checking endpoint for size 2...");
            given()
                .when().get("/api/v1/gods/greek")
                .then()
                .statusCode(200)
                .body("$", hasSize(2));
        });
         log.info("Second synchronization complete");

        // Verify final state (no duplicates added)
        log.info("Performing final GET request");
        given()
            .when().get("/api/v1/gods/greek")
            .then()
            .statusCode(200)
            .body("$", hasSize(2))
            .body("[0].name", equalTo("Zeus"))
            .body("[1].name", equalTo("Hera"));
        log.info("Test synchronization duplicates successful");
    }
} 