package com.search.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Configuration class for setting up the OpenAPI documentation for the application.
 * This class defines the basic information displayed in the OpenAPI documentation,
 * including the title, description, and version of the API
 */
@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Search-Service API")
                        .description("An API that manages calls to Google-Service API and Local Database")
                        .version("v0.1"));
    }
}
