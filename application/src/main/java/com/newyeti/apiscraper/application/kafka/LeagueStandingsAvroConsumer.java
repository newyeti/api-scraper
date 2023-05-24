package com.newyeti.apiscraper.application.kafka;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.model.avro.schema.League;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
public class LeagueStandingsAvroConsumer implements AvroConsumer<String, League>{

    private CountDownLatch latch = new CountDownLatch(1);
    private Object payload;

    @KafkaListener(topics = "${avro.topic.standings}", groupId = "league-standings", errorHandler = "avroConsumerErrorHandler")
    public void receive(ConsumerRecord<String, League> consumerRecord) {
        log.info("received payload={}", consumerRecord.toString());
        payload = consumerRecord.value();
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

}
