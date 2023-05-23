package com.newyeti.apiscraper.adapter.beakon.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class KafkaConfig {

    @Value("${avro.topic.standings}")
    private String standingsTopic;

}
