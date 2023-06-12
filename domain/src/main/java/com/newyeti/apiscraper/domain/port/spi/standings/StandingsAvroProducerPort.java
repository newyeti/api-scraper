package com.newyeti.apiscraper.domain.port.spi.standings;

import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.kafka.AvroProducerPort;

@Component
public interface StandingsAvroProducerPort extends AvroProducerPort<League> {
    
}
