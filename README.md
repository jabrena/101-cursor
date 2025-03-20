# 101 Cursor AI

```bash
#slides
jwebserver -p 8000 -d "$(pwd)/docs/"

#structurizr
docker run -it --rm -p 8080:8080 -v $(pwd)/docs-analysis/structurizr:/usr/local/structurizr structurizr/onpremises:2024.12.07

open http://localhost:8080

./mvnw versions:display-dependency-updates
./mvnw versions:display-plugin-updates
./mvnw versions:display-property-updates
```

## References

- https://www.cursor.com/
- https://revealjs.com/installation/
- https://editor-next.swagger.io/
- https://structurizr.com/
- https://docs.structurizr.com/lite
- https://hub.docker.com/r/structurizr/lite/tags
- https://hub.docker.com/r/structurizr/onpremises/tags
- https://www.plantuml.com/plantuml/uml/