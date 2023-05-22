package com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.StandingsDto;
import com.newyeti.apiscraper.domain.model.avro.schema.Standings;

@Mapper(config = StructMapperConfig.class)
public interface StandingsMapper {
        
    List<Standings> mapToStandingList(List<List<StandingsDto>> value);

    Standings toStandings(StandingsDto standingsDto);

    default Standings mapToStandings(List<StandingsDto> value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        StandingsDto standingsDto = value.get(0);
        return toStandings(standingsDto);
    }

}
