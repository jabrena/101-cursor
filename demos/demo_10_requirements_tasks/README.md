# General demo // Task management

## Examples

```bash
#Step 1
Use @1000-create-prd.mdc
Here's the feature @20250428-1.md

1. Provide a REST API to retrieve information about Greek Gods.
2. An operator who will use the REST API.
4. Yes, only the list. Yes.
5. Fixed business rule. A log message.
6. No extra metrics


1. Provide a REST API to retrieve information about Greek Gods.
2. An operator who will use the REST API.
3.1 Insert only new gods
3.2 3 retries
4. Return full list
5.1 Empty list
5.2 Yes, 10 seconds
6. No
7. No Success metrics
```

```bash
Now take @PRD-GREEK-GODS-SERVICE.md and create tasks using @1001-generate-tasks-from-prd.mdc

Please start on 1.1 and use @1002-task-list.mdc
```

```bash
Common code snipet to fix.

    // Default constructor for JPA
    public ExternalGreekGod() {
    }

    public ExternalGreekGod(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

---

kill -9 $(lsof -ti:8080)
./mvnw clean quarkus:dev
http://localhost:8080/q/dev-ui/extensions

pkill -f java
./mvnw clean verify
./mvnw liquibase:dropAll
./mvnw clean spring-boot:run
curl http://localhost:8080/api/v1/gods/



quarkus ext ls
quarkus ext list --concise -i -s jdbc
quarkus ext lsquarkus ext list -i -s rest
quarkus ext lsquarkus ext list -i -s panache

```