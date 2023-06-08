package com.newyeti.apiscraper.domain.services.kafka;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

import org.apache.kafka.common.errors.SerializationException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.port.spi.kafka.AvroProducerPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class AvroProducerService<T> implements AvroProducerPort<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    @Override
    public boolean send(String topic, String id, T obj) {
        log.info("Sending message to kafka topic={}", topic);
        boolean success = true;

        try{
            kafkaTemplate.send(topic, id, obj);
            CompletableFuture<SendResult<String, T>> future =
                kafkaTemplate.send(topic, id, obj);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("message sent to topic={} successfully", topic);
                    future.complete(result);
                    postSendMessage(true);
                } else {
                    log.error("Unable to send message due to: {}", ex.getMessage(), ex);
                    future.completeExceptionally(ex);
                    postSendMessage(false);
                }
            });
        } catch (SerializationException ex) {
            log.error("unable to serialize message", ex);
            success = false;
        }
    
        return success;
    }

    @Override
    public void postSendMessage(boolean status) {
        throw new UnsupportedOperationException("Unimplemented method 'postSendMessage'");
    }
}
