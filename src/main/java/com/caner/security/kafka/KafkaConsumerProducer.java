package com.caner.security.kafka;

import com.caner.security.model.Artist;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
//@ConditionalOnProperty(
//        value = "spring.profiles.active",
//        havingValue = "prod",
//        matchIfMissing = true)
public class KafkaConsumerProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    private KafkaTemplate<String, Artist> kafkaTemplate2;

    public KafkaConsumerProducer(KafkaTemplate<String, String> kafkaTemplate,
                                 KafkaTemplate<String, Artist> kafkaTemplate2) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplate2 = kafkaTemplate2;
    }

    public void sendMessage(String msg) {
        kafkaTemplate.send(KafkaConfig.KAFKA_TOPIC_1, msg);
    }

    @KafkaListener(topics = KafkaConfig.KAFKA_TOPIC_1, groupId = KafkaConfig.KAFKA_GROUP, containerFactory = "kafkaListenerContainerFactory")
    public void consume(@Payload String msg,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("KAFKA CONSUME [" +msg+ "] [" +partition+ "]");
    }


    public void sendMessage2(Artist msg) {
        kafkaTemplate2.send(KafkaConfig.KAFKA_TOPIC_2, msg);
    }

    @KafkaListener(topics = KafkaConfig.KAFKA_TOPIC_2, groupId = KafkaConfig.KAFKA_GROUP_2, containerFactory = "kafkaListenerContainerFactory2")
    public void consume2(@Payload Artist msg,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("KAFKA CONSUME_2 [" +msg.toString()+ "] [" +partition+ "]");
    }

}
