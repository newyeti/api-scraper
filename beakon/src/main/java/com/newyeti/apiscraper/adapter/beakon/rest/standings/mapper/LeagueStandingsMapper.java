package com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper;

import org.mapstruct.Mapper;

import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.LeagueDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.StandingsDto;
import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.model.avro.schema.Standing;

import static org.mapstruct.factory.Mappers.getMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeagueStandingsMapper {
    
    LeagueStandingsMapper LEAGUE_STANDING_MAPPER = getMapper(LeagueStandingsMapper.class);

    League toModel(LeagueDto leagueDto);

    List<List<Standing>> mapToListOfStanding(List<List<StandingsDto>> value);

    List<Standing> mapToStanding(List<StandingsDto> value);
   
}
