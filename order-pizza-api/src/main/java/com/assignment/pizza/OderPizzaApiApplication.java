package com.assignment.pizza;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class OderPizzaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OderPizzaApiApplication.class, args);
	}
}
