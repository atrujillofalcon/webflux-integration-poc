package com.eci.sterling.api.webfluxintegration.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
@IntegrationComponentScan(basePackages = "com.eci.sterling.api.webfluxintegration.integration")
public class WebfluxIntegrationPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxIntegrationPocApplication.class, args);
	}
}
