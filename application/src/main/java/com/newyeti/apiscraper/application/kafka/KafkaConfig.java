package com.newyeti.apiscraper.application.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.newyeti.apiscraper.domain.model.avro.schema.League;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class KafkaConfig {

    @Value("${avro.topic.standings}")
    private String standingsTopic;

    @Bean
    @Profile({"dev", "test"})
    public <T, E> KafkaTemplate<String, League> kafkaTemplate() {
        return new KafkaTemplate<String, League>(producerFactory());
    }

    @Bean
    @Profile({"dev", "test"})
    public ProducerFactory<String, League> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

}
