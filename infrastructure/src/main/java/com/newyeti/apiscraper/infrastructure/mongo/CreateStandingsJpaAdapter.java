package com.newyeti.apiscraper.infrastructure.mongo;

import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.standings.CreateStandingsJpaPort;

@Component
public class CreateStandingsJpaAdapter implements CreateStandingsJpaPort{

    @Override
    public void save(League league) {
    }
    
}
