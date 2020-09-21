package com.alexbro.onlinebank.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@SpringBootApplication
@ComponentScan(value = "com.alexbro.onlinebank")
@EntityScan(value = "com.alexbro.onlinebank.core")
@EnableTransactionManagement
@EnableScheduling
public class OnlinebankApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinebankApplication.class, args);
    }

}
