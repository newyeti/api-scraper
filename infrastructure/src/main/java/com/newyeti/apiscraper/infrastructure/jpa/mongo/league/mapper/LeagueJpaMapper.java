package com.newyeti.apiscraper.infrastructure.jpa.mongo.league.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.league.entity.LeagueEntity;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.mapper.StructMapperConfig;

@Mapper(config = StructMapperConfig.class)
public interface LeagueJpaMapper {
    
    @Mapping(target = "leagueId", source = "id")
    @Mapping(target = "id", source = "id", ignore = true)
    LeagueEntity toLeagueEntity(LeagueStandings leagueStandings);

}
