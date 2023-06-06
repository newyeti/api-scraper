package com.newyeti.apiscraper.adapter.injester.jpa.standings.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.newyeti.apiscraper.adapter.injester.jpa.standings.entity.LeagueStandingsEntity;

public interface LeagueStandingsRepository extends MongoRepository<LeagueStandingsEntity, String>{
    
    @Query("{legueId: '?0', season: '?1'}")
    LeagueStandingsEntity findByLeagueIdAndSeason(int leagueId, int season);
}
