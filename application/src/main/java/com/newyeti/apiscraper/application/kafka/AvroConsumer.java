package com.newyeti.apiscraper.application.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface AvroConsumer<K, V> {
    void receive(ConsumerRecord<K, V> consumerRecord);
}
