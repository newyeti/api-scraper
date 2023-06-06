package com.newyeti.apiscraper.adapter.injester.jpa.standings.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.newyeti.apiscraper.adapter.injester.jpa.standings.entity.LeagueStandingsEntity;

public interface LeagueStandingsRepository extends MongoRepository<LeagueStandingsEntity, String>{
    
}
