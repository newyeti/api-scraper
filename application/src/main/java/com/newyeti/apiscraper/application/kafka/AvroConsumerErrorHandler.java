package com.newyeti.apiscraper.application.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.application.exception.AvroException;

import lombok.extern.slf4j.Slf4j;

@Service(value = "avroConsumerErrorHandler")
@Slf4j
public class AvroConsumerErrorHandler implements ConsumerAwareListenerErrorHandler {

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        log.warn("avro consumer error, unable to consume message: {} because {}", message.getPayload(), exception);
        throw new AvroException("avro consumer exception", exception);
    }
    
}
