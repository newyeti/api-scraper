package com.newyeti.apiscraper.domain.port.api.standings;

import com.newyeti.apiscraper.domain.model.avro.schema.League;

public interface LeagueStandingsServicePort {
    
    void sendToKafkaTopic(String topic, League league);

    void saveToDb(League league);

    League findByLeagueIdAndSeason(int leagueId, int season);

}
