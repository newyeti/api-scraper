package com.newyeti.apiscraper.application.service.standings;

import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.api.standings.LeagueStandingsServicePort;

@Service
public class LeagueStandingApplicationService implements LeagueStandingsServicePort{

    @Override
    public void send(League league) {
        
    }
    
}
