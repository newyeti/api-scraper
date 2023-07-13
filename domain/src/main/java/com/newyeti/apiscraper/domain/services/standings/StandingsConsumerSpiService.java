package com.newyeti.apiscraper.domain.services.standings;

import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;
import com.newyeti.apiscraper.domain.port.spi.BusinessServiceSpi;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StandingsConsumerSpiService implements BusinessServiceSpi<LeagueStandings>{
    
    @Override
    public void postProcess(LeagueStandings leagueStandings) {
        log.info("do something here after message is received.");
    }

}
