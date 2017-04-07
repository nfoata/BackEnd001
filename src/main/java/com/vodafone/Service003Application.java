package com.vodafone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Service003Application {

	public static void main(String[] args) {
		SpringApplication.run(Service003Application.class, args);
	}
}
