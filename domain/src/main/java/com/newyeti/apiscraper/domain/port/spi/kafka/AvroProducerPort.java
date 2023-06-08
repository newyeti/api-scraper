package com.newyeti.apiscraper.domain.port.spi.kafka;

public interface AvroProducerPort<T> {
    boolean send(String topic, String id, T t);
    void postSendMessage(boolean status);
}
