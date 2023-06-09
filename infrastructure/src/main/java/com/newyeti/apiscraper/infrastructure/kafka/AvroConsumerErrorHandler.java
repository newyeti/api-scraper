package com.newyeti.apiscraper.infrastructure.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.common.exception.AvroException;

import lombok.extern.slf4j.Slf4j;

@Service(value = "avroConsumerErrorHandler")
@Slf4j
public class AvroConsumerErrorHandler implements ConsumerAwareListenerErrorHandler {

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        log.warn("avro consumer error, unable to consume message id: {} because {}", message.getHeaders().getId(), exception);
        throw new AvroException("avro consumer exception", exception);
    }
    
}
