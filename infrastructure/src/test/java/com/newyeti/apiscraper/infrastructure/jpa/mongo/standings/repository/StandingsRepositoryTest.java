package com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.entity.LeagueStandingsEntity;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.mapper.LeagueStandingsJpaMapper;

@SpringBootTest
@Testcontainers
@DirtiesContext
public class StandingsRepositoryTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");
    
    @Autowired
    private StandingsRepository standingsRepository;

    @Autowired
    private LeagueStandingsJpaMapper leagueStandingsJpaMapper;

    private League league;

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", mongoDBContainer::getHost);
        registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort);
        registry.add("spring.data.mongodb.database", () -> "testdb");
        registry.add("spring.data.mongodb.user", () -> "test_user");
        registry.add("spring.data.mongodb.password", () -> "test_password");
        System.out.println("------------------------------------------------------------");
        System.out.println("Mongo Host: " + mongoDBContainer.getHost());
        System.out.println("Mongo Port: " + mongoDBContainer.getFirstMappedPort());
        System.out.println("Mongo URI: " + mongoDBContainer.getReplicaSetUrl());
        System.out.println("------------------------------------------------------------");
    }

    @BeforeAll
    static void setUp() {
        mongoDBContainer.start();;
    }

    @Test
    public void givenStandingsData_whenCreateStandings_thenSave(){
        givenLeague();
        LeagueStandingsEntity standingsEntity = leagueStandingsJpaMapper.toLeagueStandingsEntity(league);
        standingsRepository.save(standingsEntity);

        Optional<LeagueStandingsEntity> dbEntity = standingsRepository.findByLeagueIdAndSeason(100, 2020);
        assertTrue(dbEntity.isPresent());
        assertEquals(standingsEntity.getId(), dbEntity.get().getId());
        assertEquals(standingsEntity.getSeason(), dbEntity.get().getSeason());
    }

    private void givenLeague() {
       league = League.newBuilder()
            .setId(100)
            .setSeason(2020)
            .setName("Premier League")
            .build();
    }

}
