package com.newyeti.apiscraper.application.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class KafkaConfig {

    @Value("${avro.producer.topic.standings}")
    private String standingsTopic;

}
