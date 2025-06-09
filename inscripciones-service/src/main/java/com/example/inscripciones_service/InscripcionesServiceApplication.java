package com.example.inscripciones_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InscripcionesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InscripcionesServiceApplication.class, args);
	}

}
