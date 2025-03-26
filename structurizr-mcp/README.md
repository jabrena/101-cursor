# Structurizr MCP

## How to run the POC

```bash
curl --header "X-Authorization:1234567890" http://localhost:9000/api/workspace

./mvnw clean verify exec:java -Dexec.mainClass="info.jab.demo.App"

{
  "workspaces": [
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