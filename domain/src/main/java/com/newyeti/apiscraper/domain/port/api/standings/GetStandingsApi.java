package com.newyeti.apiscraper.domain.port.api.standings;

import com.newyeti.apiscraper.common.exception.ServiceException;
import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;

public interface GetStandingsApi {
    LeagueStandings findByLeagueIdAndSeason(int leagueId, int season) throws ServiceException;
}
