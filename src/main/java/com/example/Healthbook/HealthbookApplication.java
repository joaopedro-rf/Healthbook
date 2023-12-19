package com.example.Healthbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class HealthbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthbookApplication.class, args);
	}

}
