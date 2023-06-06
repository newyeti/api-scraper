package com.newyeti.apiscraper.adapter.injester.jpa.standings.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.newyeti.apiscraper.adapter.injester.jpa.standings.entity.LeagueStandingsEntity;
import com.newyeti.apiscraper.domain.model.avro.schema.League;

@Mapper(config = StructMapperConfig.class)
public interface LeagueStandingsJpaMapper {
    
    @Mapping(target = "leagueId", source = "id")
    LeagueStandingsEntity toLeagueStandingsEntity(League league);

}
