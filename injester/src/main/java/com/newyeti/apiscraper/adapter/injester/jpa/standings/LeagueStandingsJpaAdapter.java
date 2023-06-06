package com.newyeti.apiscraper.adapter.injester.jpa.standings;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.adapter.injester.jpa.standings.entity.LeagueStandingsEntity;
import com.newyeti.apiscraper.adapter.injester.jpa.standings.mapper.LeagueStandingsJpaMapper;
import com.newyeti.apiscraper.adapter.injester.jpa.standings.repository.LeagueStandingsRepository;
import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.standings.LeagueStandingsJpaPort;

import io.micrometer.observation.annotation.Observed;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class LeagueStandingsJpaAdapter implements LeagueStandingsJpaPort {

    private final LeagueStandingsRepository leagueStandingsRepository;
    private final LeagueStandingsJpaMapper leagueStandingsJpaMapper;

    @Override
    @Observed(name = "league-standings-entity-save", 
        contextualName = "mongo-league-standings-entity-save")
    @WithSpan
    public void save(League league) {
        leagueStandingsRepository.save(leagueStandingsJpaMapper.toLeagueStandingsEntity(league));
    }

    @Override
    @Observed(name = "league-standings-findByLeagueIdAndSeason", 
    contextualName = "mongo-league-standings-find-by-leagueId-and-season")
    @WithSpan
    public League findByLeagueIdAndSeason(int leagueId, int season) {
        League result =  null;
        LeagueStandingsEntity entity = leagueStandingsRepository.findByLeagueIdAndSeason(leagueId, leagueId);
        if(Objects.isNull(entity)) {
            log.info("league standings not found for league={} and season={}", leagueId, season);
        } else {
            result = leagueStandingsJpaMapper.toLeague(entity);
        }
        return result;
    }
    
}
