package com.newyeti.apiscraper.infrastructure.jpa.mongo;

import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;
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
    private final LeagueStandingsJpaMapper leagueStandingsJpaMapper;

    @Override
    public void save(String key, LeagueStandings leagueStandings) {
        if(standingsRepository.countByUuid(key) > 0) {
            log.info("duplicate message with key={}", key);
            return;
        }

        log.debug("saving to database with key={}", key);
        LeagueStandingsEntity standingsEntity = leagueStandingsJpaMapper.toLeagueStandingsEntity(leagueStandings);
        standingsEntity.setUuid(key);
        standingsRepository.save(standingsEntity);
    }
    
}
