package com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper;

import org.mapstruct.Mapper;

import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.LeagueDto;
import com.newyeti.apiscraper.domain.model.avro.schema.League;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(imports = League.class)
public interface LeagueStandingsMapper {
    
    LeagueStandingsMapper LEAGUE_STANDING_MAPPER = getMapper(LeagueStandingsMapper.class);

    LeagueDto toDto(League league);

    League toDomain(LeagueDto leagueDto);

}
