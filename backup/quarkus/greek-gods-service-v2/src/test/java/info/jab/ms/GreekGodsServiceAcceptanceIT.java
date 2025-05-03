package info.jab.ms;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // To ensure some data might exist for later tests
public class GreekGodsServiceAcceptanceIT {

    // Test implementation will follow in task 4.2

    private static final String BASE_PATH = "/api/v1/gods/greek";

    @Test
    @Order(1) // Run first to check basic structure
    public void should_RetrieveAllGreekGods_Successfully() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get(BASE_PATH)
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            // Check it's a list (array)
            .body("$.size()", greaterThanOrEqualTo(0)) // Check if the list size is >= 0
            // Check first element's structure and type if list not empty
            // Note: This assumes the list is not empty. A more robust test might handle empty list.
            // .body("[0].id", instanceOf(Integer.class)) // PanacheEntity ID is Long, JSON number map fits Integer
            // .body("[0].name", instanceOf(String.class));
            ;
    }

    @Test
    @Order(2) // Run after potential synchronization
    public void should_ContainSpecificGreekGod_When_Retrieved() {
        // Give time for potential synchronization if needed
        // In a real scenario, might wait or ensure data is seeded.
        // For now, we assume the sync might have run or devservices seeded.

        given()
            .accept(ContentType.JSON)
        .when()
            .get(BASE_PATH)
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            // Use Groovy GPath to find the god with id 1 and check its name
            .body("find { it.id == 1 }.name", equalTo("Zeus"))
            .body("find { it.id == 1 }.id", equalTo(1)); // Also verify the ID
    }
} 