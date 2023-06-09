package com.newyeti.apiscraper.infrastructure.standings;

import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.standings.StandingsAvroConsumerPort;

@Component
public class StandingsAvroConsumerAdapter implements StandingsAvroConsumerPort{

    @Override
    public void receive(String topic, League league) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'receive'");
    }

    @Override
    public void postProcess(League league) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postProcess'");
    }
    
}
