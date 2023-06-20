package com.newyeti.apiscraper.infrastructure.kafka;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.port.spi.kafka.AvroConsumerPort;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
@RequiredArgsConstructor
public class AvroConsumerService<K, V> implements AvroConsumerPort<K, V> {

    private CountDownLatch latch = new CountDownLatch(1);

    @Override
    public void receive(ConsumerRecord<K, V> consumerRecord) {
        log.info("received payload from topic={}, key={}", 
            consumerRecord.topic(),
            consumerRecord.key());
            
        K key = consumerRecord.key();
        V payload = consumerRecord.value();
        if (Objects.isNull(payload)) {
            log.error("received 'null' on topic={}");
        } else {
            process(key, payload);
        }
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    @Override
    public void process(K key, V payload) {
        throw new UnsupportedOperationException("Unimplemented method 'process'");
    }

}
