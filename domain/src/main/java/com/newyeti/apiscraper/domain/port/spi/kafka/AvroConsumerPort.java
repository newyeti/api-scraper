package com.newyeti.apiscraper.domain.port.spi.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface AvroConsumerPort<K, V> {
    void receive(ConsumerRecord<K, V> consumerRecord);
    void postReceiveMessage(V payload);
}
