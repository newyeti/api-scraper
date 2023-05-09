package com.newyeti.apiscraper.beakon.adapter.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class KafkaConfig {

    @Value("${avro.topic.name}")
    private String topic;

}
