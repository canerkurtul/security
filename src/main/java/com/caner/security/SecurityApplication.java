package com.caner.security;

import com.caner.security.kafka.KafkaConsumerProducer;
import com.caner.security.model.Artist;
import com.caner.security.redis.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

// @ComponentScan(basePackages = { "com.caner.customer", "com.caner.common" })
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SecurityApplication {

    private final Logger logger = LoggerFactory.getLogger(SecurityApplication.class);

    private final KafkaConsumerProducer kafkaProducer;
    private final RedisClient redisClient;

    public SecurityApplication(@Autowired(required = false)KafkaConsumerProducer kafkaProducer,
                               @Autowired(required = false)RedisClient redisClient) {
        this.kafkaProducer = kafkaProducer;
        this.redisClient = redisClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Scheduled(fixedDelay = 3000)
    public void periodicLog() {
        String msg = "heartbeat " + System.currentTimeMillis();
        System.out.println("\n" + msg);
        if (kafkaProducer != null) {
            kafkaProducer.sendMessage("KAFKA " + msg);
            kafkaProducer.sendMessage2(new Artist("Artist " + System.currentTimeMillis(), "TR"));
        }

        String millis = "" + System.currentTimeMillis();
        if (redisClient != null) {
            redisClient.write("MYKEY", millis);
            redisClient.read("MYKEY");

            redisClient.writeArtist(new Artist(millis, "TR"));
            redisClient.readArtist(millis);
        }

        logger.warn("Schedule task finished");
    }


    @Bean
    CommandLineRunner startup_runner() {
        return args -> {
            System.out.println("Execute at app startup");
        };
    }

}
