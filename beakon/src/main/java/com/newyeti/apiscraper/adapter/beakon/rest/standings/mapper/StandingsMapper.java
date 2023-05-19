package com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.StandingsDto;
import com.newyeti.apiscraper.domain.model.avro.schema.Standings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StandingsMapper {
    
    List<List<Standings>> mapToListOfStanding(List<List<StandingsDto>> value);
    
    List<Standings> mapToStanding(List<StandingsDto> value);
}
