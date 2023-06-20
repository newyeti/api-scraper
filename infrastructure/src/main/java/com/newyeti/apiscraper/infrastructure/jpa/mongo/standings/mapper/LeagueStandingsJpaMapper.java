package com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.entity.LeagueStandingsEntity;

@Mapper(config = StructMapperConfig.class)
public interface LeagueStandingsJpaMapper {
    
    @Mapping(target = "leagueId", source = "id")
    @Mapping(target = "id", source = "id", ignore = true)
    LeagueStandingsEntity toLeagueStandingsEntity(LeagueStandings leagueStandings);

    @Mapping(target = "id", source = "leagueId")
    LeagueStandings toLeagueStandings(LeagueStandingsEntity entity);

}
