package com.newyeti.apiscraper.infrastructure.kafka;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.port.spi.kafka.AvroConsumerPort;

import io.micrometer.observation.annotation.Observed;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
public class AvroConsumerService<K, V> implements AvroConsumerPort<K, V> {

    private CountDownLatch latch = new CountDownLatch(1);
    private V payload;

    @Override
    @KafkaListener(topics = "${avro.consumer.topics}", 
        groupId = "${avro.consumer.groupId}", 
        errorHandler = "avroConsumerErrorHandler")
    public Optional<V> receive(ConsumerRecord<K, V> consumerRecord) {
        log.info("received payload from topic={}", consumerRecord.topic());
        payload = consumerRecord.value();
        if (Objects.isNull(payload)) {
            log.error("received 'null' payload=League on topic={}");
        } 

        return Optional.of(payload);
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

}
