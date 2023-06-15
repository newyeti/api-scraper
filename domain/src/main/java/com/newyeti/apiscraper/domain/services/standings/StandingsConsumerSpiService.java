package com.newyeti.apiscraper.domain.services.standings;

import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.BusinessServiceSpi;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StandingsConsumerSpiService implements BusinessServiceSpi<League>{
    
    @Override
    public void postProcessReceivedMessage(League league) {
        log.info("do something here after message is received.");
    }

}
