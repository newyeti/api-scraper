package com.newyeti.apiscraper.domain.services.standings;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.kafka.AvroProducerPort;

public class StandingsAvroProducerService implements AvroProducerPort<League>{

    @Override
    public boolean send(String topic, String id, League t) {
        throw new UnsupportedOperationException("Unimplemented method 'send'");
    }
    
}
