package com.furkanisitan.countrycityapi.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Country City API")
                        .description("An Spring Boot Rest API for managing countries and cities.<br />You can find out more about filtering, paging and sorting at [github.com/furkanisitan/country-city-api](https://github.com/furkanisitan/country-city-api#filtering-paging-and-sorting).")
                        .version("1.0.0")
                        .contact(new Contact().name("Furkan Işıtan").url("https://furkanisitan.com/").email("furkanisitan96@gmail.com"))
                        .license(new License().name("MIT License").url("https://raw.githubusercontent.com/furkanisitan/country-city-api/main/LICENSE")))
                .addServersItem(new Server().url("http://localhost:8080"));
    }

}