package com.caner.security.kafka;

import com.caner.security.models.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Artist> kafkaTemplate2;

    public void sendMessage(String msg) {
        kafkaTemplate.send(KafkaConfig.KAFKA_TOPIC_1, msg);
    }

    @KafkaListener(topics = KafkaConfig.KAFKA_TOPIC_1, groupId = KafkaConfig.KAFKA_GROUP, containerFactory = "kafkaListenerContainerFactory")
    public void consume(@Payload String msg,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("CONSUME [" +msg+ "] [" +partition+ "]");
    }


    public void sendMessage2(Artist msg) {
        kafkaTemplate2.send(KafkaConfig.KAFKA_TOPIC_2, msg);
    }

    @KafkaListener(topics = KafkaConfig.KAFKA_TOPIC_2, groupId = KafkaConfig.KAFKA_GROUP_2, containerFactory = "kafkaListenerContainerFactory2")
    public void consume2(@Payload Artist msg,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("CONSUME_2 [" +msg.toString()+ "] [" +partition+ "]");
    }

}
