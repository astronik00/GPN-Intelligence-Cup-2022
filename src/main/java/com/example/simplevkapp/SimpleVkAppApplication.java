package com.example.simplevkapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SimpleVkAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleVkAppApplication.class, args);
	}

}
