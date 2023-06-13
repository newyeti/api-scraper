package com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.entity.LeagueStandingsEntity;

public interface StandingsRepository extends MongoRepository<LeagueStandingsEntity, String>{
    
    @Query("{legueId: '?0', season: '?1'}")
    LeagueStandingsEntity findByLeagueIdAndSeason(int leagueId, int season);
}
