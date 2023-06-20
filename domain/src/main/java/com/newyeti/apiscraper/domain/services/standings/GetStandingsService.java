package com.newyeti.apiscraper.domain.services.standings;

import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;
import com.newyeti.apiscraper.domain.port.api.standings.GetStandingsApi;

public class GetStandingsService implements GetStandingsApi {

    @Override
    public LeagueStandings findByLeagueIdAndSeason(int leagueId, int season) {
        throw new UnsupportedOperationException("Unimplemented method 'findByLeagueIdAndSeason'");
    }
    
}
