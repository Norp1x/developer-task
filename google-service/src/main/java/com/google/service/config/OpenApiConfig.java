package com.google.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Configuration class for setting up OpenAPI documentation for the application.
 * This configuration defines metadata such as the title, description, and version of the API.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Google-Service API")
                        .description("An API that manages query calls to Google from Search-Service")
                        .version("v0.1"));
    }
}
