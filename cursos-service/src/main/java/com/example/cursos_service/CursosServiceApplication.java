package com.example.cursos_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CursosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosServiceApplication.class, args);
	}

}
