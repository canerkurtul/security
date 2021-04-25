package com.caner.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

// @ComponentScan(basePackages = { "com.caner.customer", "com.caner.common" })
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }


    @Bean
    CommandLineRunner startup_runner() {
        return args -> {
            System.out.println("Execute at app startup");
        };
    }

}
