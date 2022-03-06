package com.furkanisitan.countrycityapi.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Country City API")
                        .description("An Spring Boot Rest API for managing countries and cities.")
                        .version("v0.0.1")
                        .contact(new Contact().name("Furkan Işıtan").url("furkanisitan.com").email("furkanisitan96@gmail.com"))
                        .license(new License().name("MIT License").url("https://raw.githubusercontent.com/furkanisitan/country-city-api/main/LICENSE")));
    }

}