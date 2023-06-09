package com.newyeti.apiscraper.infrastructure.standings;

import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.standings.StandingsAvroProducerPort;

@Component
public class StandingsAvroProducerAdapter implements StandingsAvroProducerPort{

    @Override
    public void send(String topic, String id, League league) {
        throw new UnsupportedOperationException("Unimplemented method 'send'");
    }

    @Override
    public void postProcess(boolean success) {
        throw new UnsupportedOperationException("Unimplemented method 'postProcess'");
    }
    
}
