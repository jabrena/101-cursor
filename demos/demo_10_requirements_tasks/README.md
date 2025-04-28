# General demo // Task management

## Examples

```bash
#Step 1
Use @1000-create-prd.mdc
Here's the feature @20250428-1.md

Now take @PRD-GREEK-GODS-SERVICE.md and create tasks using @1001-generate-tasks-from-prd.mdc

Please start on 1.1 and use @1002-task-list.mdc

---

kill -9 $(lsof -ti:8080)
./mvnw clean quarkus:dev
http://localhost:8080/q/dev-ui/extensions

pkill -f java
./mvnw clean verify
./mvnw liquibase:dropAll
./mvnw clean spring-boot:run
curl http://localhost:8080/api/v1/gods

quarkus ext ls
quarkus ext list --concise -i -s jdbc
quarkus ext lsquarkus ext list -i -s rest
quarkus ext lsquarkus ext list -i -s panache

```