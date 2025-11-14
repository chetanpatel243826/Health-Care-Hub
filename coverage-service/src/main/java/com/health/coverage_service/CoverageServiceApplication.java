package com.health.coverage_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CoverageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoverageServiceApplication.class, args);
	}

	@Bean
	public RestTemplate retTemplate() {
		return new RestTemplate();
	}
}
