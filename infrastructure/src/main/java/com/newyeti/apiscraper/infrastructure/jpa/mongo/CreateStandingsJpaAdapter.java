package com.newyeti.apiscraper.infrastructure.jpa.mongo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;
import com.newyeti.apiscraper.domain.port.spi.standings.CreateStandingsJpaPort;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.league.entity.LeagueEntity;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.league.mapper.LeagueJpaMapper;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.league.repository.LeagueRepository;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.entity.LeagueStandingsEntity;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.mapper.LeagueStandingsJpaMapper;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.repository.StandingsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateStandingsJpaAdapter implements CreateStandingsJpaPort{

    private final StandingsRepository standingsRepository;
    private final LeagueStandingsJpaMapper leagueStandingsJpaMapper;
    private final LeagueRepository leagueRepository;
    private final LeagueJpaMapper leagueJpaMapper;

    @Override
    @Transactional
    public void save(String key, LeagueStandings leagueStandings) {
        if(standingsRepository.countByUuid(key) > 0) {
            log.info("duplicate message with key={}", key);
            return;
        }

        if(leagueRepository.countByLeagueId(leagueStandings.getId()) <= 0) {
            log.info("league not found....saving league with id={}, name={}", 
            leagueStandings.getId(), leagueStandings.getName());
            LeagueEntity leagueEntity = leagueJpaMapper.toLeagueEntity(leagueStandings);
            leagueRepository.save(leagueEntity);
        }

        log.debug("saving to database with key={}", key);
        
        LeagueStandingsEntity standingsEntity = leagueStandingsJpaMapper.toLeagueStandingsEntity(leagueStandings);
        standingsEntity.setUuid(key);
        standingsRepository.save(standingsEntity);
    }
    
}
