# 101 Cursor AI

```bash
#slides
jwebserver -p 8000 -d "$(pwd)/docs/"
open http://localhost:8000

#structurizr
docker run -it --rm -p 9000:8080 -v $(pwd)/structurizr:/usr/local/structurizr structurizr/onpremises:2024.12.07
open http://localhost:9000

./mvnw versions:display-dependency-updates
./mvnw versions:display-plugin-updates
./mvnw versions:display-property-updates
```

## Examples

```bash
Use the @20240320-1.md as Prompt and analyze implement & test the following images to understand the required development.

pkill -f java
./mvnw clean verify
./mvnw liquibase:dropAll
./mvnw clean spring-boot:run
curl http://localhost:8080/api/v1/gods
```

## References

- https://www.cursor.com/
- https://docs.cursor.com/
- https://revealjs.com/installation/
- https://editor-next.swagger.io/
- https://structurizr.com/
- https://docs.structurizr.com/onpremises
- https://docs.structurizr.com/onpremises/authentication/file
- https://hub.docker.com/r/structurizr/lite/tags
- https://hub.docker.com/r/structurizr/onpremises/tags
- https://www.plantuml.com/plantuml/uml/
- https://codely.com/en/blog
