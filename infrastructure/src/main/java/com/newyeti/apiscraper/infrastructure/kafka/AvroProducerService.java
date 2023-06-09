package com.newyeti.apiscraper.infrastructure.kafka;

import java.util.concurrent.CompletableFuture;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.port.spi.kafka.AvroProducerPort;

import io.micrometer.observation.annotation.Observed;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class AvroProducerService<T> implements AvroProducerPort<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    @Value("${kafka.producer.send.enabled:true}")
    private boolean sendEnabled;

    @Override
    @Observed(name = "kafka.avro.producer.send", contextualName = "avro-producer-send-service")
    @WithSpan
    public boolean send(String topic, String id, T obj) {
        if (!sendEnabled) {
            log.info("Kafka Producer - sending message disabled. Please enable propert '{}' to start sending messages", "kafka.producer.send.enabled");
            return true;
        }

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
        }
    
        return success;
    }

}
