package com.newyeti.apiscraper.infrastructure.jpa.mongo;

import java.time.Instant;
import java.util.Optional;

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

        saveLeagueDocument(leagueStandings);
        upsertStandingsDocument(key, leagueStandings);
        
    }

    private void saveLeagueDocument(LeagueStandings leagueStandings) {
        if(leagueRepository.countByLeagueId(leagueStandings.getId()) <= 0) {
            log.info("league not found....saving league with id={}, name={}", 
            leagueStandings.getId(), leagueStandings.getName());
            LeagueEntity leagueEntity = leagueJpaMapper.toLeagueEntity(leagueStandings);
            leagueEntity.setUpdatedOn(Instant.now().toString());
            leagueRepository.save(leagueEntity);
        }
    }

    private void upsertStandingsDocument(String key, LeagueStandings leagueStandings) {
        log.debug("saving to database with key={}", key);

        Optional<LeagueStandingsEntity> dbEntity = standingsRepository
            .findByLeagueIdAndSeason(leagueStandings.getId(), leagueStandings.getSeason());

        LeagueStandingsEntity standingsEntity = leagueStandingsJpaMapper.toLeagueStandingsEntity(leagueStandings);
        standingsEntity.setUuid(key);
        standingsEntity.setUpdatedOn(Instant.now().toString());

        // Update standings array if already present and update date do not match
        if (dbEntity.isPresent()) {
            String lastUpdatedOn = dbEntity.get().getStandings().get(0).getUpdate();
            String currentUpdateDate = leagueStandings.getStandings().get(0).getUpdate();
            if(!lastUpdatedOn.equalsIgnoreCase(currentUpdateDate)) {
                standingsEntity.setId(dbEntity.get().getId());
                standingsRepository.save(standingsEntity);
            }
        } else {
            standingsRepository.save(standingsEntity);
        }
    }
    
}
