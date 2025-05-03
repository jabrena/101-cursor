# PRD: Greek Gods Service

## 1. Introduction/Overview

This document outlines the requirements for the Greek Gods Service. The primary goal is to create a microservice that provides information about Greek gods via a REST API. The service will maintain its own database, periodically synchronized with an external data source (`my-json-server`). This aims to provide a stable and potentially more performant internal endpoint for accessing Greek god data compared to directly relying on the external source.

## 2. Goals

*   Provide a reliable RESTful API endpoint for retrieving a list of Greek gods.
*   Maintain an up-to-date internal database of Greek gods by periodically synchronizing with an external source.
*   Ensure the service is resilient to temporary failures of the external data source.

## 3. User Stories

*   **As an API Operator, I want to retrieve a complete list of Greek gods via a single API call, so that I can populate systems or interfaces requiring this data.**

## 4. Functional Requirements

1.  **FR1:** The system **must** expose a REST API endpoint at `/api/v1/gods/greek`.
2.  **FR2:** A GET request to `/api/v1/gods/greek` **must** return a JSON array of Greek god objects.
3.  **FR3:** Each Greek god object in the response **must** contain an `id` (integer) and a `name` (string), as defined in the `greekController-oas.yaml` specification.
4.  **FR4:** The system **must** maintain a PostgreSQL database to store Greek god information (`id`, `name`), following the `schema.sql` definition.
5.  **FR5:** The system **must** periodically fetch the list of Greek gods from the external API endpoint (`https://my-json-server.typicode.com/jabrena/latency-problems/greek`).
6.  **FR6:** The synchronization process (FR5) **must** run every 10 seconds.
7.  **FR7:** The system **must** update its internal database (FR4) with the data fetched from the external API (FR5). This involves adding new gods and potentially updating existing ones if their names change (though the external data structure only shows `id` and `name`). Handle potential data conflicts (e.g., unique constraint on `name`).
8.  **FR8:** If the external API (FR5) fails or is unreachable during synchronization, the system **must** log an informative error message.
9.  **FR9:** The API endpoint (FR1) **must** continue to serve data from its internal database even if the synchronization process (FR5) fails. (Serving potentially stale data is acceptable).

## 5. Non-Goals (Out of Scope)

*   Providing endpoints to fetch individual gods by ID.
*   Providing endpoints to create, update, or delete gods via the API.
*   Implementing search or filtering capabilities on the god list.
*   Handling gods from mythologies other than Greek.
*   User authentication or authorization for the API endpoint.
*   Advanced error handling beyond logging for synchronization failures.

## 6. Design Considerations (Optional)

*   The API design should adhere to the provided OpenAPI specification: `greekController-oas.yaml`.
*   The interaction with the external service should use the client generated from `my-json-server-oas.yaml`.
*   Refer to the component diagram (`structurizr-Component-001-thumbnail.png`) and sequence diagram (`uml-sequence-diagram.png`) for architectural guidance.

## 7. Technical Considerations (Optional)

*   The service will be built using Quarkus 3.21.1 and Java 21+.
*   Dependencies listed in the initial prompt (`20250428-1.md`) should be used.
*   Database interactions should utilize Hibernate Panache with a PostgreSQL database.
*   The synchronization logic should be implemented using Quarkus Scheduler (`@Scheduled`).
*   Development environment should use Quarkus DevServices for PostgreSQL as configured in the prompt.

## 8. Success Metrics

*   Successful completion of the Acceptance Test defined in `greek_gods.feature`.
*   The service runs without errors in development mode (`quarkus:dev`) for at least 20 seconds, demonstrating successful initial synchronization and API availability.
*   Absence of critical errors in logs related to core functionality (API serving, database interaction, basic synchronization).

## 9. Open Questions

*   How should database conflicts during synchronization be handled specifically (e.g., if the external service provides a god with a name that already exists but has a different ID)? Should it update the existing record, ignore the new one, or fail the sync? (Current assumption: Ignore or update based on ID if possible, log otherwise). 