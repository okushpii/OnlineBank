package com.alexbro.onlinebank.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(value = "com.alexbro.onlinebank")
@EntityScan(value = "com.alexbro.onlinebank.core")
@EnableTransactionManagement
public class OnlinebankApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinebankApplication.class, args);
	}

}
