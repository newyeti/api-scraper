package com.newyeti.apiscraper.application.kafka;

import java.util.concurrent.CompletableFuture;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.errors.TimeoutException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.port.api.spi.kafka.AvroProducerPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class AvroProducer<T> implements AvroProducerPort<T> {

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
                } else {
                    log.error("Unable to send message due to: {}", ex.getMessage(), ex);
                    future.completeExceptionally(ex);
                }
            });
        } catch (SerializationException ex) {
            log.error("unable to serialize message", ex);
            success = false;
        } catch (TimeoutException ex) {
            log.error("time occurred when sending message", ex);
            success = false;
        }
    
        return success;
    }
}
