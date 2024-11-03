package com.google.service.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Configuration class for application-wide settings.
 * This class provides a configured {@link RestTemplate} bean with custom connection
 * and read timeouts, allowing control over HTTP request timing.
 */
@Configuration
public class AppConfig {

    private final Duration READ_TIMEOUT_IN_MINUTES = Duration.of(1, ChronoUnit.MINUTES);
    private final Duration CONNECT_TIMEOUT_IN_SECONDS = Duration.of(30, ChronoUnit.SECONDS);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(CONNECT_TIMEOUT_IN_SECONDS)
                .setReadTimeout(READ_TIMEOUT_IN_MINUTES)
                .build();
    }
}
