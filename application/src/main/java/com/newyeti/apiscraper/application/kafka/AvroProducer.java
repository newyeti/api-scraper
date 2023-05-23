package com.newyeti.apiscraper.application.kafka;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.api.spi.kafka.AvroProducerPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class AvroProducer implements AvroProducerPort<League> {

    private final KafkaTemplate<String, League> kafkaTemplate;
    
    @Override
    public boolean send(String topic, League league) {
        log.info("Sending league standings data to kafka topic={}", topic);
        
        CompletableFuture<SendResult<String, League>> future =
            kafkaTemplate.send(topic, String.valueOf(league.getId()), league);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("message sent successfully. message={}", result);
               // success = future.complete(result);
            } else {
                log.error("Unable to send message due to: {}", ex.getMessage(), ex);
                //future.completeExceptionally(ex);
            }
        });

        return true;
    }
}
