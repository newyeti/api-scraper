package com.newyeti.apiscraper.infrastructure.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.kafka.AvroProducerPort;
import com.newyeti.apiscraper.infrastructure.kafka.AvroProducerService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DomainConfiguration {

    private final KafkaTemplate<String, League> standingKafkaTemplate;
    
    @Bean
    @Qualifier("standingsAvroProducerPort")
    public AvroProducerPort<League> standingsAvroProducerPort(){
        return new AvroProducerService<League>(standingKafkaTemplate);
    }

}
