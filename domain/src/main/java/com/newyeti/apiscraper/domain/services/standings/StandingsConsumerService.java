package com.newyeti.apiscraper.domain.services.standings;

import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.BusinessServiceSpi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StandingsConsumerService implements BusinessServiceSpi<League>{
    
    @Override
    public void postProcessReceivedMessage(League league) {
        log.info("do something here after message is received.");
    }

}
