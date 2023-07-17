package com.newyeti.apiscraper.infrastructure.jpa.mongo.league.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.newyeti.apiscraper.infrastructure.jpa.mongo.league.entity.LeagueEntity;

public interface LeagueRepository extends MongoRepository<LeagueEntity, String>{
    int countByLeagueId(int leagueId);
    LeagueEntity findByLeagueId(int leagueId);
}
