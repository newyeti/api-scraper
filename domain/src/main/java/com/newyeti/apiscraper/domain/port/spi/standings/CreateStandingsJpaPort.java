package com.newyeti.apiscraper.domain.port.spi.standings;

import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;

public interface CreateStandingsJpaPort {
    void save(String key, LeagueStandings leagueStandings);
}
