package br.com.dsleite.cloudparking.config;

import java.util.Arrays;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Component
@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI parkingOpenAPI(){
        return new OpenAPI()
            .info(new Info().title("Parking API")
            .description("Simple Parking API made for the DIO Bootcamp, by Eduardo Augusto")
            .version("v0.0.1")
            .license(new License().name("Apache 2.0")))
            .externalDocs(new ExternalDocumentation()
            .description("Documentation")
            .url("https://github.com/eduardodsl/dio-spring-deploy"));
    }

}
