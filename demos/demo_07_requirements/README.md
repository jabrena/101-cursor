# General demo

```bash
#structurizr
docker run -it --rm -p 9000:8080 -v $(pwd)/structurizr:/usr/local/structurizr structurizr/onpremises:2024.12.07
open http://localhost:9000

curl --header "X-Authorization:1234567890" http://localhost:9000/api/workspace

```

## Improvement

```bash
@workspace.dsl analyze the structurizr desing using the different areas to be deployed in Azure @ms.png what external services should be interesting to be added in the desing?
```

## Examples

```bash
Use the @20250405-1.md as Prompt and analyze implement & test the following images to understand the required development.

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