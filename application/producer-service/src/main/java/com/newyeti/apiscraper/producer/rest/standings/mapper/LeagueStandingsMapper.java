package com.newyeti.apiscraper.producer.rest.standings.mapper;

import org.mapstruct.Mapper;

import com.newyeti.apiscraper.producer.rest.standings.dto.LeagueStandingsDto;
import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;

@Mapper(config = StructMapperConfig.class, uses = StandingsMapper.class)
public interface LeagueStandingsMapper {
    
    LeagueStandings toLeagueStandings(LeagueStandingsDto leagueDto);
   
}
