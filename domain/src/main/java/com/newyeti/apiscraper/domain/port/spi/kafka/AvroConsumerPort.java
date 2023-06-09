package com.newyeti.apiscraper.domain.port.spi.kafka;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface AvroConsumerPort<K, V> {
    Optional<V> receive(ConsumerRecord<K, V> consumerRecord);
    void process(V payload);
}
