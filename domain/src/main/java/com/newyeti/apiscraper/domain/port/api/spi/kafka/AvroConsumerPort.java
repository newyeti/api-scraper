package com.newyeti.apiscraper.domain.port.api.spi.kafka;

public interface AvroConsumerPort<T> {
    void consume(T t);   
}
