package com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.LeagueDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.StandingsDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.StatisticsDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.TeamDto;
import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.model.avro.schema.Standing;

import static com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper.LeagueStandingsMapper.LEAGUE_STANDING_MAPPER;

public class LeagueStandingsMapperTest {
    
    @Test
    public void givenLeagueDto_whenMapToLeague_thenCorrect() {
        LeagueDto leagueDto = getLeagueDto();
        League league = LEAGUE_STANDING_MAPPER.toModel(leagueDto);

        assertNotNull(league, "league object should not be null");
        assertEquals(league.getId(), leagueDto.getId());
        assertEquals(league.getName(), leagueDto.getName());
        assertEquals(league.getCountry(), leagueDto.getCountry());
        assertEquals(league.getFlag(), leagueDto.getFlag());   
    }

    @Test
    public void givenStandingsDtoList_whenMapToStandingListModel_thenCorrect() {
        LeagueDto leagueDto = getLeagueDto();
        League league = LEAGUE_STANDING_MAPPER.toModel(leagueDto);
       
        assertNotNull(league, "league should not be null");
        assertEquals(league.getStandings().size(),  leagueDto.getStandings().size());
        assertEquals(league.getStandings().get(0).size(), leagueDto.getStandings().get(0).size());

        StandingsDto standingsDto = leagueDto.getStandings().get(0).get(0);
        Standing standing = league.getStandings().get(0).get(0);
        
        assertEquals(standing.getTeam().getName(), standingsDto.getTeam().getName());
        assertEquals(standing.getGoalsDiff(), standingsDto.getGoalsDiff());
        assertEquals(standing.getForm(), standingsDto.getForm());
        assertEquals(standing.getAll().getPlayed(), standingsDto.getAll().getPlayed());
        assertEquals(standing.getAll().getWin(), standingsDto.getAll().getWin());
        assertEquals(standing.getAll().getLose(), standingsDto.getAll().getLose());
    
    }

    private LeagueDto getLeagueDto() {
        return LeagueDto.builder()
            .id(123)
            .name("Premier League")
            .country("England")
            .flag("England Flag")
            .season(2020)
            .standings(getStandingsDtoList())
            .build();
    }

    private List<List<StandingsDto>> getStandingsDtoList() {
        List<List<StandingsDto>> standingsDtos = new ArrayList<>();
        List<StandingsDto> standings = new ArrayList<>();
        standings.add(
        StandingsDto.builder()
            .rank(1)
            .goalsDiff(5)
            .form("WWWWW")
            .team(TeamDto.builder().name("Manchester United").build())
            .all(StatisticsDto.builder().played(10).win(8).draw(1).lose(1).build())
            .home(StatisticsDto.builder().played(5).win(5).draw(0).lose(0).build())
            .away(StatisticsDto.builder().played(5).win(3).draw(1).lose(1).build())
            .build()
        );
        standingsDtos.add(standings);
        return standingsDtos;
    }

}
