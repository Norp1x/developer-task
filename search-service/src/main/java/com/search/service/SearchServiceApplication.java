package com.search.service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication(scanBasePackages = {"com.search.service", "com.search.project.kafka"})
public class SearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }
}
