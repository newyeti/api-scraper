package com.newyeti.apiscraper.application.service.standings;

import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.api.standings.LeagueStandingsServicePort;
import com.newyeti.apiscraper.domain.port.spi.kafka.AvroProducerPort;
import com.newyeti.apiscraper.domain.port.spi.standings.LeagueStandingsJpaPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeagueStandingAppService implements LeagueStandingsServicePort {

    private final AvroProducerPort<League> avroProducerPort;
    private final LeagueStandingsJpaPort leagueStandingsJpaPort;

    @Override
    public void sendToKafkaTopic(String topic, League league) {
        avroProducerPort.send(topic, String.valueOf(league.getId()), league);
    }

    @Override
    public void saveToDb(League league) {
        leagueStandingsJpaPort.save(league);
    }

    @Override
    public League findByLeagueIdAndSeason(int leagueId, int season) {
       return leagueStandingsJpaPort.findByLeagueIdAndSeason(leagueId, season);
    }
    
}
