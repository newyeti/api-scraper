package com.newyeti.apiscraper.application.kafka;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
public class AvroConsumer {

    private CountDownLatch latch = new CountDownLatch(1);
    private Object payload;

    @KafkaListener(topics = "league.topic.v1", groupId = "league-standings")
    public void receive(ConsumerRecord<?,?> consumerRecord) {
        log.info("received payload={}", consumerRecord.toString());
        payload = consumerRecord.value();
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

}
