package com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.LeagueDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.StandingsDto;
import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.model.avro.schema.Standing;

import static org.mapstruct.factory.Mappers.getMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeagueStandingsMapper {
    
    LeagueStandingsMapper LEAGUE_STANDING_MAPPER = getMapper(LeagueStandingsMapper.class);

    LeagueDto toDto(League league);

    League toModel(LeagueDto leagueDto);

    // @Named("mapToStandingDto")
    // default List<StandingsDto> mapToStandingDto(Standing standings) {

    // }

    // default List<Standing> mapToStandingModel(StandingsDto standings) {

    // }

}
