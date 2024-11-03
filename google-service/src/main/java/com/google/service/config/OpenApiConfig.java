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

    private static final String GOOGLE_SERVICE_API_TITLE = "Google-Service API";
    private static final String GOOGLE_SERVICE_API_DESCRIPTION = "An API that manages query calls to Google from Search-Service";
    private static final String GOOGLE_SERVICE_API_VERSION = "v0.1";

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(GOOGLE_SERVICE_API_TITLE)
                        .description(GOOGLE_SERVICE_API_DESCRIPTION)
                        .version(GOOGLE_SERVICE_API_VERSION));
    }
}
