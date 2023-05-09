package com.newyeti.apiscraper.domain.port.api.spi.kafka;

public interface AvroProducerPort<T> {
    
    void send(T t);
}
