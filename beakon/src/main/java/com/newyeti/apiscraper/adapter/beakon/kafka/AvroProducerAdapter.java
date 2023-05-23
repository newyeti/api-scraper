package com.newyeti.apiscraper.adapter.beakon.kafka;

import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.api.spi.kafka.AvroProducerPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class AvroProducerAdapter implements AvroProducerPort<League> {

    private final KafkaConfig kafkaConfig;

    @Override
    public void send(League t) {
        log.info("Sending data to kafka topic={}", kafkaConfig.getStandingsTopic());
        log.info(t.toString());
    }
    
}
