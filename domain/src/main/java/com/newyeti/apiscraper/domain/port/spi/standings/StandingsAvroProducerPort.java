package com.newyeti.apiscraper.domain.port.spi.standings;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.kafka.AvroProducerPort;

public interface StandingsAvroProducerPort extends AvroProducerPort<League> {
    
}
