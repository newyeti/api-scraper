package com.newyeti.apiscraper.infrastructure.mongo;

import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.standings.CreateStandingsJpaPort;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CreateStandingsJpaAdapter implements CreateStandingsJpaPort{

    @Override
    public void save(League league) {
        log.info("Saving to database");
    }
    
}
