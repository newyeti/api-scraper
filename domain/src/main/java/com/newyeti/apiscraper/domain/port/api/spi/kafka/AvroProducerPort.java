package com.newyeti.apiscraper.domain.port.api.spi.kafka;

public interface AvroProducerPort<T> {
    
    boolean send(String topic, String id, T t);
}
