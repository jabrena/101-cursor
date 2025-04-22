package info.jab.ms;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.instanceOf;

@QuarkusIntegrationTest
public class GreekGodsServiceAcceptanceIT {

    @Test
    public void testGetGodsEndpoint() {
        given()
        .when()
            .get("/gods")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            // Verify the response body is a JSON list (array)
            // We don't assert specific content as it depends on the scheduler
            // and external API state during the test run.
            .body("", instanceOf(List.class));
    }

    // Note: This test runs against the packaged application (JAR or native).
    // It verifies the endpoint is available and returns the correct structure.
    // Verifying specific data might require mocking the external dependency
    // even in integration tests or ensuring the scheduler runs predictably.
} 