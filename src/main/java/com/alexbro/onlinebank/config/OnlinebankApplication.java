package com.alexbro.onlinebank.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.alexbro.onlinebank")
public class OnlinebankApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinebankApplication.class, args);
	}

}
