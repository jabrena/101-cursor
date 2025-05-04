# Spring Boot Backup

```bash
pkill -f java
./mvnw clean verify
./mvnw clean spring-boot:run

http://localhost:8080/api-docs
http://localhost:8080/swagger-ui/index.html

curl http://localhost:8080/api/v1/gods/greek

./mvnw versions:display-dependency-updates
./mvnw versions:display-plugin-updates
./mvnw versions:display-property-updates
```