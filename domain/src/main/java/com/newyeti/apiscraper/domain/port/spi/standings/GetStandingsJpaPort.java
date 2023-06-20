package com.newyeti.apiscraper.domain.port.spi.standings;

import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;

public interface GetStandingsJpaPort {
    LeagueStandings findByLeagueIdAndSeason(int leagueId, int season);
}
