package com.apiRestCrud.apiResCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "repository")
@EntityScan(basePackages = "model")
@ComponentScan(basePackages = {"controller","com.apiRestCrud.apiResCrud"})
public class ApiResCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiResCrudApplication.class, args);
	}

}
