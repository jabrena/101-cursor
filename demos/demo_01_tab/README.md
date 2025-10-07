# Demo 01 / Learn to use Tab

## Goal: Show how tab works

- https://my-json-server.typicode.com/jabrena/latency-problems

```bash
./mvnw clean verify
./mvnw clean spring-boot:run

curl -X GET "http://localhost:8080/api/v1/gods/greek"
curl -X GET "http://localhost:8080/api/v1/gods/roman"

#new endpoint
curl -X GET "http://localhost:8080/api/v1/gods/indian"
```
