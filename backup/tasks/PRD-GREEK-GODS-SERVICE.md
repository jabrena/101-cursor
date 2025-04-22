# Product Requirements Document: Greek Gods Service

## 1. Introduction/Overview

This document outlines the requirements for the `greek-gods-service`, a Java microservice built using the Quarkus framework. The service will periodically fetch Greek God data (specifically names) from a third-party JSON API, store this data in a PostgreSQL database, and expose it via a simple REST endpoint. The primary purpose is to provide a reliable, internally accessible API presenting a list of Greek Gods.

## 2. Goals

*   Develop a RESTful microservice using Quarkus 3.21.1, Java 24, and Maven.
*   Implement a REST endpoint (`/gods`) to retrieve all stored Greek God names.
*   Integrate with a third-party API (defined in `my-json-server-oas.yaml`) to fetch god data.
*   Implement a scheduled task to periodically fetch data from the third-party API and update the local PostgreSQL database every 10 seconds.
*   Utilize PostgreSQL for data persistence, leveraging Quarkus DevServices for local development.
*   Ensure the project builds cleanly and passes all defined tests (Unit, Integration, Acceptance).
*   Structure the application using a standard layered architecture.

## 3. User Stories

*   **As an API consumer:** I want to call the `/gods` endpoint to get a JSON list of all available Greek God names, so that I can display or use this information in my application.
*   **As a developer:** I want the service to automatically keep the list of gods updated by fetching from the external source every 10 seconds, so that the API provides relatively fresh data without manual intervention.

## 4. Functional Requirements

1.  **Project Setup:**
    *   The service MUST be created using Quarkus 3.21.1, Java 24, and Maven, with the base package `info.jab.ms`.
    *   Maven Wrapper (`mvnw`) MUST be included.
    *   The project MUST be located in a directory named `greek-gods-service`.
2.  **Dependencies:**
    *   The `pom.xml` MUST include the following Quarkus dependencies: `quarkus-rest`, `quarkus-smallrye-health`, `quarkus-scheduler`, `quarkus-smallrye-openapi`, `quarkus-jdbc-postgresql`, `quarkus-hibernate-orm-panache`, `quarkus-arc`, `quarkus-rest-client-jackson`.
3.  **Maven Plugins:**
    *   The `pom.xml` MUST include and configure:
        *   `maven-enforcer-plugin` (with rules for dependency convergence, banned dependencies like Lombok, Java/Maven versions).
        *   `versions-maven-plugin`.
        *   `maven-surefire-plugin` (for unit tests).
        *   `maven-failsafe-plugin` (for integration/acceptance tests).
4.  **Application Structure:**
    *   The codebase MUST follow a layered architecture within the `info.jab.ms` package:
        *   `domain`: Data entities/models.
        *   `resources`: REST controllers (`GreekController`).
        *   `services`: Business logic (e.g., `GodUpdateService`, `GodQueryService`).
        *   `repository`: Data access layer (`GodRepository` using Panache).
    *   The main application class (`info.jab.ms.MainApplication`) MUST be appropriately annotated for Quarkus.
5.  **REST API (`GreekController`):**
    *   Implement the REST controller based on the provided `greekController-oas.yaml`.
    *   It MUST provide a `GET /gods` endpoint.
    *   The `GET /gods` endpoint MUST return a JSON array containing the names of all Greek Gods currently stored in the database.
6.  **Third-Party Integration:**
    *   An HTTP client MUST be generated/implemented based on `my-json-server-oas.yaml` to interact with the external Greek Gods API.
7.  **Data Fetching & Storage (`GodUpdateService`):**
    *   A scheduled service MUST run every 10 seconds.
    *   This service MUST use the generated HTTP client to fetch data from the third-party API.
    *   It MUST process the response and update the `gods` table in the PostgreSQL database with the latest list of god names.
8.  **Database (`GodRepository`):**
    *   The service MUST use Hibernate ORM with Panache for database interactions.
    *   The database schema defined in `schema.sql` MUST be used.
    *   `schema.sql` MUST be placed in `src/main/resources`.
    *   The application MUST be configured to use Quarkus DevServices for PostgreSQL during development, using the specified properties (port 5430, db name `greekgods`, init script).
    *   Hibernate ORM MUST be configured for `drop-and-create` during development and to log SQL.
9.  **Logging:**
    *   Basic logging SHOULD be implemented in key methods (e.g., controller actions, service execution, repository access).
10. **Testing:**
    *   An acceptance test (`GreekGodsServiceAcceptanceIT.java`) using REST Assured MUST be implemented.
    *   This test MUST verify the functionality described in `greek_gods.feature`.
    *   The command `./mvnw clean verify` MUST execute successfully, running all tests.
    *   The command `./mvnw quarkus:dev` MUST run the application locally without errors for at least 20 seconds, demonstrating the scheduler and database interactions are functioning.

## 5. Non-Goals (Out of Scope)

*   Implementing user authentication or authorization for the `/gods` endpoint.
*   Implementing API rate limiting or throttling.
*   Providing API endpoints to retrieve, create, update, or delete individual gods.
*   Implementing complex data validation rules beyond what's necessary for storage.
*   Defining specific performance benchmarks (e.g., response time SLAs).
*   Advanced operational monitoring or tracing beyond basic health checks.

## 6. Design Considerations

*   Refer to the C4 Component diagram (`structurizr-Component-001-thumbnail.png`) for the intended high-level architecture.
*   Refer to the UML Sequence diagram (`uml-sequence-diagram.png`) for expected component interactions.
*   The REST API design MUST conform strictly to `greekController-oas.yaml`.
*   The third-party client interaction MUST align with `my-json-server-oas.yaml`.
*   The database schema MUST match `schema.sql`.

## 7. Technical Considerations

*   **Framework:** Quarkus 3.21.1
*   **Language:** Java 24
*   **Build Tool:** Maven (with Wrapper)
*   **Database:** PostgreSQL (via DevServices locally)
*   **Persistence:** Hibernate ORM with Panache
*   **Scheduling:** Quarkus Scheduler (`quarkus-scheduler`)
*   **API Documentation:** SmallRye OpenAPI (`quarkus-smallrye-openapi`)
*   **REST Client:** Quarkus REST Client (`quarkus-rest-client-jackson`)
*   **Testing:** JUnit 5, REST Assured, Maven Surefire/Failsafe
*   **Architecture:** Layered (Resources, Services, Repository, Domain)

## 8. Success Metrics

*   Successful execution and passing of all tests via `./mvnw clean verify`.
*   Successful execution of the REST Assured acceptance test (`GreekGodsServiceAcceptanceIT.java`) covering the scenarios in `greek_gods.feature`.
*   Ability to run the application locally using `./mvnw quarkus:dev` without errors for at least 20 seconds, confirming the scheduler fetches data and the API endpoint returns results.

## 9. Open Questions

*   None at this time. 