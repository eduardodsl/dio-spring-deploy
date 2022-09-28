# Parking Management API

Spring Framework application for the DIO bootcamp. This application has a few differences from the original proposed by the bootcamp instructor, as by the time of development of this application, some dependencies became depreciated.

* Springfox was replaced by [SpringDoc OpenApi](https://springdoc.org/)
* Added patch method for parking editing, as suggested by the teacher
* Additional dependencies on `pom.xml` to support TestContainers

## TestContainers

This application was setup to test the Postgres database using TestContainers, so you're required to have Docker installed

Docker image built for tests:
```sh
docker run --name parking-db -p 5432:5432 -e POSTGRES_DB=[database] -e POSTGRES_USER=[login] -e POSTGRES_PASSWORD [password] -d postgres:10-alpine
```

## Authentication

Although the Swagger documentation is configured to be public, you will be required to authenticate to test the parts of the API that are behind Spring Security, the suggested username and password is included in the example properties file below. These credentials are also defined in the test file `CloudParkingApplicationTests.java`: 

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

spring.security.user.name=user
spring.security.user.password=testpswd@-123*
```

## Notes

Currently the CSRF is disabled for testing purposes, please check the file `config/SecurityConfiguration.java` if removing this configurationn is needed 