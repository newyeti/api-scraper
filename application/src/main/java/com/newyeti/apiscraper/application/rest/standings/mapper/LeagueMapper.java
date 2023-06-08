package com.newyeti.apiscraper.application.rest.standings.mapper;

import org.mapstruct.Mapper;

import com.newyeti.apiscraper.application.rest.standings.dto.LeagueDto;
import com.newyeti.apiscraper.domain.model.avro.schema.League;

@Mapper(config = StructMapperConfig.class, uses = StandingsMapper.class)
public interface LeagueMapper {
    
    League toLeague(LeagueDto leagueDto);
   
}
