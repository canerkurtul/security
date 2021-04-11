package com.caner.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

// @ComponentScan(basePackages = { "com.caner.customer", "com.caner.common" })
@SpringBootApplication
@EnableAsync
@EnableScheduling
// @EnableCaching
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Scheduled(fixedDelay = 2000)
    public void periodicLog() {
        System.out.println("heartbeat " + System.currentTimeMillis());
    }

}
