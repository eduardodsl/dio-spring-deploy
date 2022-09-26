# dio-spring-deploy

Spring framework deploy for parking management for the DIO bootcamp Spring Framework Challenge

* Springfox was replaced by OpenApi
* Added patch method parking editing

Docker image for testing:
```sh
docker run --name parking-db -p 5432:5432 -e POSTGRES_DB=parking -e POSTGRES_USER=[login] -e POSTGRES_PASSWORD [password] -d postgres:10-alpine
```

Properties file used:
```properties
server.error.include-exception=false
server.error.include-stacktrace=never
server.error.include-message=always

spring.datasource.url=jdbc:postgresql://[server]:[port]/[database]?useTimezone=true&serverTimezone=UTC&useLegacyDate
spring.datasource.username=[login]
spring.datasource.password=[password]

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate-format-sql=true
```