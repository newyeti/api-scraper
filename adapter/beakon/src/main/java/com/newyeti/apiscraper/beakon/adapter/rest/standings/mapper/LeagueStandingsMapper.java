package com.newyeti.apiscraper.beakon.adapter.rest.standings.mapper;

import org.mapstruct.Mapper;

import com.newyeti.apiscraper.domain.model.avro.schema.League;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(imports = League.class)
public class LeagueStandingsMapper {
    
    LeagueStandingsMapper LEAGUE_STANDING_MAPPER = getMapper(LeagueStandingsMapper.class);

    

}
