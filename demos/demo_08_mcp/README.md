# MCP Servers

## Data

```bash
source ./EXPORT.sh
./mcp-stats.sh

jwebserver -p 8001 -d "$(pwd)/demos/demo_06_mcp/"
open http://localhost:8001/
```

## MCP Examples

```
jbang cache clear
jbang catalog list jabrena
jbang catalog list mcp-java

jbang cursor-mcp-config@jabrena --help
jbang cursor-mcp-config@jabrena --show
jbang cursor-mcp-config@jabrena --backup
jbang cursor-mcp-config@jabrena --replace example/mcp0.json
jbang cursor-mcp-config@jabrena --replace example/mcp1.json
```

## Prompt

```bash
start the stopwatch mcp
create a new quarkus app from the cli using quarkus create app execute this command from the folder: demos/demo_08_mcp
stop the stopwatch mpc
tell me how long in seconds the example was created
```

## Verify development

```bash
./mvnw quarkus:dev
```