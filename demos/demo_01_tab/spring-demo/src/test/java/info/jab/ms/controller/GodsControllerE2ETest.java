package info.jab.ms.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.jupiter.api.DisplayName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GodsControllerE2ETest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("GET /api/v1/gods/greek should return the list of Greek gods")
    void shouldGetGreekGods() {
        given()
        .when()
            .get("/api/v1/gods/greek")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("$", hasSize(greaterThan(5)))
            .body("$", everyItem(instanceOf(String.class)));
    }

    @Test
    @DisplayName("GET /api/v1/gods/roman should return the list of Roman gods")
    void shouldGetRomanGods() {
        given()
        .when()
            .get("/api/v1/gods/roman")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("$", hasSize(greaterThan(5)))
            .body("$", everyItem(instanceOf(String.class)));
    }
}
