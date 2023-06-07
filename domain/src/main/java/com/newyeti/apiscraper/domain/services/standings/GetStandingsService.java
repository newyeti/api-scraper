package com.newyeti.apiscraper.domain.services.standings;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.api.standings.GetStandingsApi;

public class GetStandingsService implements GetStandingsApi {

    @Override
    public League findByLeagueIdAndSeason(int leagueId, int season) {
        throw new UnsupportedOperationException("Unimplemented method 'findByLeagueIdAndSeason'");
    }
    
}
