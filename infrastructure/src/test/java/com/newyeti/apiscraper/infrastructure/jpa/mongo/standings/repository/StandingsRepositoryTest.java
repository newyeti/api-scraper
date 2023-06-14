package com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.repository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.StandingsDataConfig;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.entity.LeagueStandingsEntity;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.mapper.LeagueStandingsJpaMapper;

@SpringBootTest
@DirtiesContext
public class StandingsRepositoryTest extends RepositoryContainerConfiguration {
 
    @Autowired
    private StandingsRepository standingsRepository;

    @Autowired
    private LeagueStandingsJpaMapper leagueStandingsJpaMapper;

    private League league;

    @Test
    public void givenStandingsData_whenCreateStandings_thenSave(){
        givenLeague(100, 2020);
        LeagueStandingsEntity standingsEntity = leagueStandingsJpaMapper.toLeagueStandingsEntity(league);
        standingsRepository.save(standingsEntity);

        Optional<LeagueStandingsEntity> dbEntity = standingsRepository.findByLeagueIdAndSeason(100, 2020);
        assertTrue(dbEntity.isPresent());
        assertEquals(standingsEntity.getId(), dbEntity.get().getId());
        assertEquals(standingsEntity.getSeason(), dbEntity.get().getSeason());
    }

    private void givenLeague(int id, int season) {
       league = StandingsDataConfig.getLeague(id, season);
    }

}
