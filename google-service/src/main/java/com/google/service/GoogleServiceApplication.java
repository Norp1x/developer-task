package com.google.service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@OpenAPIDefinition
@SpringBootApplication
public class GoogleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleServiceApplication.class, args);
	}

}
