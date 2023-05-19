package com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.LeagueDto;
import com.newyeti.apiscraper.domain.model.avro.schema.League;

import org.mapstruct.InjectionStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = StandingsMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LeagueMapper {
    
    League toLeague(LeagueDto leagueDto);
   
}
