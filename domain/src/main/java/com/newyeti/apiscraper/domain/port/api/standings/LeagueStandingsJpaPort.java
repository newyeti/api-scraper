package com.newyeti.apiscraper.domain.port.api.standings;

import com.newyeti.apiscraper.domain.model.avro.schema.League;

public interface LeagueStandingsJpaPort {
    
    void save(League league);

}
