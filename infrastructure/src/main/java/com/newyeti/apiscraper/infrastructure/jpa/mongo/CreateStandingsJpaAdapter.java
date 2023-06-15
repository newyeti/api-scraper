package com.newyeti.apiscraper.infrastructure.jpa.mongo;

import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.standings.CreateStandingsJpaPort;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.entity.LeagueStandingsEntity;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.mapper.LeagueStandingsJpaMapper;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.repository.StandingsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateStandingsJpaAdapter implements CreateStandingsJpaPort{

    private final StandingsRepository standingsRepository;
    private LeagueStandingsJpaMapper leagueStandingsJpaMapper;

    @Override
    public void save(League league) {
        log.debug("Saving to database");
        LeagueStandingsEntity standingsEntity = leagueStandingsJpaMapper.toLeagueStandingsEntity(league);
        standingsRepository.save(standingsEntity);
    }
    
}
