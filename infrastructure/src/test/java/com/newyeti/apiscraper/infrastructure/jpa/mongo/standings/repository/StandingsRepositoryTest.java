package com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

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
    private LeagueStandingsEntity standingsEntity;

    @BeforeEach
    void beforeEach() {
        league = null;
        standingsEntity = null;
    }

    @AfterEach
    void tearDown() {
        standingsRepository.deleteAll();
    }

    @Test
    public void givenStandingsData_whenCreateStandings_thenSave(){
        givenLeague(100, 2020);
        convertToEntity();
        saveToDb();

        Optional<LeagueStandingsEntity> dbEntity = standingsRepository.findByLeagueIdAndSeason(100, 2020);
        assertTrue(dbEntity.isPresent());
        assertEquals(100, dbEntity.get().getLeagueId());
        assertEquals(2020, dbEntity.get().getSeason());
    }

    @Test
    public void givenStandingsData_whenFindByLeagueId_thenShouldReturn() {
        givenLeague(100, 2020);
        convertToEntity();
        saveToDb();

        List<LeagueStandingsEntity> dbEntity = standingsRepository.findByLeagueId(100);
        assertTrue(dbEntity.size() == 1);
        assertEquals(100, dbEntity.get(0).getLeagueId());
        assertEquals(2020, dbEntity.get(0).getSeason());
    }

    @Test
    public void givenStandingsData_whenFindBySeason_thenShouldReturn() {
        givenLeague(100, 2020);
        convertToEntity();
        saveToDb();

        List<LeagueStandingsEntity> dbEntity = standingsRepository.findBySeason(2020);
        assertTrue(dbEntity.size() == 1);
        assertEquals(100, dbEntity.get(0).getLeagueId());
        assertEquals(2020, dbEntity.get(0).getSeason());
    }

    @Test
    public void givenManyStandingsData_whenFindByLeagueId_thenReturnList() {
        IntStream.range(0, 3)
            .forEach(i -> {
                givenLeague(100, 2020+i);
                convertToEntity();
                saveToDb();
            });

        List<LeagueStandingsEntity> dbEntities = standingsRepository.findByLeagueId(100);
        assertTrue(dbEntities.size() == 3);
        
        IntStream.range(0, 3)
            .forEach(i -> {
                assertEquals(100, dbEntities.get(i).getLeagueId());
                assertEquals(2020+i, dbEntities.get(i).getSeason());
            });
    }


    private void givenLeague(int id, int season) {
       league = StandingsDataConfig.getLeague(id, season);
    }

    private void convertToEntity() {
        standingsEntity = leagueStandingsJpaMapper.toLeagueStandingsEntity(league);
    }

    private void saveToDb() {
        standingsRepository.save(standingsEntity);
    }

}
