# Greek Gods Microservice

A Spring Boot microservice that provides information about Greek gods via a REST API.

## Architecture

This microservice is part of a system designed to provide information about Greek gods. The architecture follows a microservices approach with the following components:

1. **Gods Rest Controller** - Provides information about Greek gods in JSON format
2. **Greek God Service** - Interacts with the Database Repository to provide Greek gods data
3. **My JSON Server Synchronizer Service** - Interacts with an external service to fetch and store Greek gods data
4. **Greek God Repository** - Spring Data JDBC repository for accessing the PostgreSQL database

## Technologies

- Java 23
- Spring Boot 3.4.3
- Spring Data JDBC
- PostgreSQL
- Docker Compose for local development
- Liquibase for database migrations
- OpenAPI for API specification and code generation
- JUnit 5 for testing

## Setup and Running

### Prerequisites

- Java 23 (or compatible version)
- Docker and Docker Compose
- Maven

### Running the Application

1. Clone the repository
2. Start the application with Docker Compose:

```bash
./mvnw spring-boot:run
```

This will start both the application and a PostgreSQL database.

### API Endpoints

- `GET /api/v1/gods/greek` - Get all Greek gods

## Development

### Database Migrations

The application uses Liquibase for database schema management. Migrations are defined in:

```
src/main/resources/db/changelog/
```

### External Service Integration

The application periodically fetches data from an external service:

```
https://my-json-server.typicode.com/jabrena/latency-problems/greek
```

The update interval can be configured using the `external.my-json-server.update-interval-seconds` property.

## Testing

Run the tests using Maven:

```bash
./mvnw test
```

The project includes:
- Unit tests
- Integration tests (using TestContainers)

## References

- https://www.cursor.com/
- https://revealjs.com/installation/
- https://editor-next.swagger.io/
- https://structurizr.com/
- https://docs.structurizr.com/onpremises
- https://docs.structurizr.com/onpremises/authentication/file
- https://hub.docker.com/r/structurizr/lite/tags
- https://hub.docker.com/r/structurizr/onpremises/tags
- https://www.plantuml.com/plantuml/uml/