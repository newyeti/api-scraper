package com.newyeti.apiscraper.beakon.adapter.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class KafkaConfig {
    @Value("${avro.topic.name}")
    private String topic;
}
