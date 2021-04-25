package com.caner.security.schedule;

import com.caner.security.SecurityApplication;
import com.caner.security.kafka.KafkaConsumerProducer;
import com.caner.security.model.Artist;
import com.caner.security.redis.RedisClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnExpression("'${spring.profiles.active}'!='local'")
@AllArgsConstructor
public class ScheduledTasks {

    private final Logger logger = LoggerFactory.getLogger(SecurityApplication.class);

    private final KafkaConsumerProducer kafkaProducer;
    private final RedisClient redisClient;

//    public ScheduledTasks(@Autowired(required = false)KafkaConsumerProducer kafkaProducer,
//                               @Autowired(required = false)RedisClient redisClient) {
//        this.kafkaProducer = kafkaProducer;
//        this.redisClient = redisClient;
//    }

    @Scheduled(fixedDelay = 3000)
    @ConditionalOnExpression("'${spring.profiles.active}'!='local'")
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
}
