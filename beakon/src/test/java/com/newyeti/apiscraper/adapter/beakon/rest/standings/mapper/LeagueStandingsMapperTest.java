package com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.LeagueDto;
import com.newyeti.apiscraper.domain.model.avro.schema.League;

import static com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper.LeagueStandingsMapper.LEAGUE_STANDING_MAPPER;

public class LeagueStandingsMapperTest {
    
    @Test
    private void givenLeagueDto_whenMapToLeague_thenCorrect() {
        LeagueDto leagueDto = LeagueDto.builder()
            .id(123)
            .name("Premier League")
            .country("England")
            .flag("England Flag")
            .build();
        League league = LEAGUE_STANDING_MAPPER.toModel(leagueDto);

        assertNotNull(league, "league object should not be null");
        assertEquals(league.getId(), leagueDto.getId());
        assertEquals(league.getName(), leagueDto.getName());
        assertEquals(league.getCountry(), leagueDto.getCountry());
        assertEquals(league.getFlag(), leagueDto.getFlag());        
    }

}
