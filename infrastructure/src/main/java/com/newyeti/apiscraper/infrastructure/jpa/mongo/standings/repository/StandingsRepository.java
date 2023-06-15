package com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.entity.LeagueStandingsEntity;

public interface StandingsRepository extends MongoRepository<LeagueStandingsEntity, String>{
    
    List<LeagueStandingsEntity> findByLeagueId(int leagueId);

    List<LeagueStandingsEntity> findBySeason(int season);

    @Query("{'leagueId' : ?0, 'season' : ?1 }")
    Optional<LeagueStandingsEntity> findByLeagueIdAndSeason(int leagueId, int season);

}
