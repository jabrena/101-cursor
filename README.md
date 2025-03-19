# 101 Cursor AI

```bash
jwebserver -p 8000 -d "$(pwd)/docs/"
docker run -it --rm -p 8080:8080 -v $(pwd)/docs-analysis/structurizr:/usr/local/structurizr structurizr/lite
open http://localhost:8080
```

## References

- https://www.cursor.com/
- https://revealjs.com/installation/
