package com.assignment.pizza;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
@Log4j2
public class OderPizzaApiApplication implements CommandLineRunner {

	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(OderPizzaApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.error(passwordEncoder.encode("123456"));
	}
}
