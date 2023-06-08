package com.newyeti.apiscraper.domain.services.kafka;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.port.spi.kafka.AvroConsumerPort;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AvroConsumerService<K, V> implements AvroConsumerPort<K, V> {

    private CountDownLatch latch = new CountDownLatch(1);
    private V payload;

    @Override
    @KafkaListener(topics = "${avro.topics}", groupId = "${avro.groupId}", errorHandler = "avroConsumerErrorHandler")
    public void receive(ConsumerRecord<K, V> consumerRecord) {
        log.info("received payload from topic={}", consumerRecord.topic());
        payload = consumerRecord.value();
        if (Objects.isNull(payload)) {
            log.error("received 'null' payload=League on topic={}");
        } else {
            postReceiveMessage(payload);
        }
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    @Override
    public void postReceiveMessage(V payload) {
        throw new UnsupportedOperationException("Unimplemented method 'postReceiveMessage'");
    }

}
