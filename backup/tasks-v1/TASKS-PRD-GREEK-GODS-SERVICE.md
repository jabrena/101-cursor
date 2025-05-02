# Task List: Greek Gods Service

Based on: `PRD-GREEK-GODS-SERVICE.md`

## Tasks

- [x] 1.0 Setup Project Structure and Database Schema
  - [x] 1.1 Create Quarkus project `greek-gods-service` with specified Maven structure, Java version, and dependencies (REST, Health, Scheduler, OpenAPI, JDBC Postgres, Panache, ARC, REST Client Jackson).
  - [x] 1.2 Add Maven Enforcer, Versions, Surefire, and Failsafe plugins to `pom.xml`.
  - [x] 1.3 Define the package structure: `info.jab.ms.domain`, `info.jab.ms.resources`, `info.jab.ms.services`, `info.jab.ms.repository`.
  - [x] 1.4 Create the `GreekGod` entity in `info.jab.ms.domain` mapping to the `greek_god` table (id, name).
  - [x] 1.5 Create the `GreekGodRepository` interface in `info.jab.ms.repository` extending `PanacheRepository<GreekGod>`.
  - [x] 1.6 Place the `schema.sql` file in `src/main/resources/`.
  - [x] 1.7 Configure `application.properties` for PostgreSQL DevServices and Hibernate ORM (`drop-and-create`, SQL logging) as specified in the prompt.
- [x] 2.0 Implement the REST API Endpoint for Reading Gods
  - [x] 2.1 Implement `GreekGodService` in `info.jab.ms.services` with a method to retrieve all gods from `GreekGodRepository`.
  - [x] 2.2 Implement `GreekController` in `info.jab.ms.resources` based on `greekController-oas.yaml`.
  - [x] 2.3 Inject `GreekGodService` into `GreekController`.
  - [x] 2.4 Implement the GET `/api/v1/gods/greek` endpoint in `GreekController` to call the service method and return the list of gods (FR1, FR2, FR3).
- [x] 3.0 Implement the External API Client and Synchronization Service
  - [x] 3.1 Generate/Implement the REST Client interface (`MyJsonServerClient`) in `info.jab.ms.services` based on `my-json-server-oas.yaml` to call the external API (FR5).
  - [x] 3.2 Implement `MyJsonServerSynchronizerService` in `info.jab.ms.services`.
  - [x] 3.3 Inject `MyJsonServerClient` and `GreekGodRepository` into `MyJsonServerSynchronizerService`.
  - [x] 3.4 Implement a scheduled method in `MyJsonServerSynchronizerService` to run every 10 seconds (`@Scheduled`) (FR6).
  - [x] 3.5 Inside the scheduled method, call the external API via `MyJsonServerClient` to fetch external gods.
  - [x] 3.6 Implement logic to update the local database via `GreekGodRepository` with the fetched gods (add new, update existing - handle potential conflicts, e.g., by ID or name) (FR7).
  - [x] 3.7 Add error handling (try-catch) around the external API call and database update to log errors if the external service is unavailable or other issues occur (FR8).
- [x] 4.0 Implement Acceptance Tests
  - [x] 4.1 Create `GreekGodsServiceAcceptanceIT.java` in `src/test/java/info/jab/ms`.
  - [x] 4.2 Use REST Assured to implement the scenarios defined in `greek_gods.feature`.
  - [x] 4.3 Ensure tests cover retrieving the list and verifying specific content (e.g., Zeus).
- [x] 5.0 Final Configuration and Verification
  - [x] 5.1 Add `@QuarkusApplication` annotation to `info.jab.ms.MainApplication.java` (if creating a custom main) - N/A (No custom main class found)
  - [x] 5.2 Add basic logging statements to key methods (e.g., controller endpoint, service methods, synchronizer start/end/error).
  - [x] 5.3 Run `./mvnw clean verify` to ensure all tests pass and the build is successful.
  - [x] 5.4 Run `./mvnw quarkus:dev` and let it run for >20 seconds, checking logs for synchronization messages and potential errors. Verify the API endpoint `http://localhost:8080/api/v1/gods/greek` is accessible and returns data.

### Relevant Files

- `greek-gods-service/pom.xml` - Project dependencies and build configuration.
- `greek-gods-service/src/main/resources/application.properties` - Database, Hibernate, and application configuration.
- `greek-gods-service/src/main/resources/schema.sql` - Database schema definition.
- `greek-gods-service/src/main/java/info/jab/ms/domain/GreekGod.java` - JPA Entity for Greek gods.
- `greek-gods-service/src/main/java/info/jab/ms/repository/GreekGodRepository.java` - Panache repository interface for database access.
- `greek-gods-service/src/main/java/info/jab/ms/resources/GreekController.java` - REST API endpoint implementation.
- `greek-gods-service/src/main/java/info/jab/ms/services/GreekGodService.java` - Service layer for handling API requests.
- `greek-gods-service/src/main/java/info/jab/ms/services/MyJsonServerClient.java` - REST Client interface for the external API.
- `greek-gods-service/src/main/java/info/jab/ms/services/MyJsonServerSynchronizerService.java` - Service containing the scheduled logic for database synchronization.
- `greek-gods-service/src/test/java/info/jab/ms/GreekGodsServiceAcceptanceIT.java` - Acceptance tests using REST Assured.
- `greek-gods-service/src/main/java/info/jab/ms/MainApplication.java` - Main application class (optional, for customization). 