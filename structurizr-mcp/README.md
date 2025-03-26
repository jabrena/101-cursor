# Structurizr MCP

## How to run the POC

```bash
docker run -it --rm -p 9000:8080 -v $(pwd)structurizr:/usr/local/structurizr structurizr/onpremises:2024.12.07

curl --header "X-Authorization:1234567890" http://localhost:9000/api/workspace

./mvnw clean verify
./mvnw clean verify exec:java -Dexec.mainClass="info.jab.structurizr.mcp.StructurizrPOC"

{
  "workspaces": [
    {
      "id": 7,
      "name": "MCP Demo",
      "description": "Sandbox to test the MCP Idea",
      "apiKey": "af19b244-8bb2-4b8a-ab25-b13d655fcb4b",
      "apiSecret": "56a77388-cf05-44d1-9e6d-21cc24799e72",
      "privateUrl": "/workspace/7",
      "publicUrl": "/share/7",
      "shareableUrl": ""
    },
    {
      "id": 1,
      "name": "Name",
      "description": "Description",
      "apiKey": "9cbe63db-012b-4ddd-bd34-2d6577d35e7b",
      "apiSecret": "08b47c68-c1ea-43e3-b4e0-6361e9ed879d",
      "privateUrl": "/workspace/1",
      "publicUrl": "/share/1",
      "shareableUrl": ""
    }
  ]
}

./mvnw versions:display-dependency-updates
./mvnw versions:display-plugin-updates
./mvnw versions:display-property-updates
```

