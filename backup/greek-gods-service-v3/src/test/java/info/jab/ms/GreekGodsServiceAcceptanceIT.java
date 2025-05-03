package info.jab.ms;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.awaitility.Awaitility.await;

import java.time.Duration;

@QuarkusTest
public class GreekGodsServiceAcceptanceIT {

    private static final Logger log = LoggerFactory.getLogger(GreekGodsServiceAcceptanceIT.class);
    private static final String BASE_PATH = "/api/v1/gods/greek";

    /**
     * Waits until the synchronizer service has likely run at least once
     * and populated the database, then checks the API response.
     */
    @Test
    public void testGetAllGreekGods() {
        log.info("Starting test: testGetAllGreekGods");

        // Wait for the scheduler to run and potentially populate the DB
        // The scheduler runs every 10s, wait a bit longer to be safe
        await().atMost(Duration.ofSeconds(15)).pollInterval(Duration.ofSeconds(1)).untilAsserted(() -> {
            log.info("Checking API endpoint...");
            given()
                .when()
                    .get(BASE_PATH)
                .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("$.size()", greaterThan(0)) // Check if the list is not empty
                    .body("[0].id", isA(Integer.class))
                    .body("[0].name", isA(String.class));
            log.info("Assertion passed: API returned non-empty list with correct structure.");
        });
        log.info("Finished test: testGetAllGreekGods");
    }

    /**
     * Waits until the synchronizer service has likely run and populated
     * the database, then specifically checks for the presence of 'Zeus'.
     */
    @Test
    public void testVerifySpecificGreekGod() {
         log.info("Starting test: testVerifySpecificGreekGod");

        // Wait for the scheduler to run and potentially populate the DB
        await().atMost(Duration.ofSeconds(15)).pollInterval(Duration.ofSeconds(1)).untilAsserted(() -> {
            log.info("Checking API endpoint for Zeus...");
            given()
                .when()
                    .get(BASE_PATH)
                .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("find { it.id == 1 }.name", equalTo("Zeus")); // Check for god with id 1 and name Zeus
             log.info("Assertion passed: Found God with ID 1 and Name Zeus.");
        });
         log.info("Finished test: testVerifySpecificGreekGod");
    }
}
