package com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.LeagueDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.StandingsDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.StatisticsDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.TeamDto;
import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.model.avro.schema.Standings;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@ComponentScan(basePackageClasses = LeagueMapper.class)
@ActiveProfiles("test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LeagueMapperTest {

    @Autowired
    private LeagueMapper leagueMapper;
    
    @Test
    public void givenLeagueDto_whenMapToLeague_thenCorrect() {
        LeagueDto leagueDto = getLeagueDto();
        League league = leagueMapper.toLeague(leagueDto);

        assertNotNull(league, "league object should not be null");
        assertEquals(leagueDto.getId(), league.getId());
        assertEquals(leagueDto.getName(), league.getName());
        assertEquals(leagueDto.getCountry(), league.getCountry());
        assertEquals(leagueDto.getFlag(), league.getFlag());   
    }

    @Test
    public void givenStandingsDtoList_whenMapToStandingListModel_thenCorrect() {
        LeagueDto leagueDto = getLeagueDto();
        League league = leagueMapper.toLeague(leagueDto);
       
        assertNotNull(league, "league should not be null");
        assertEquals(leagueDto.getStandings().size(),  league.getStandings().size());
        assertEquals(leagueDto.getStandings().get(0).size(), league.getStandings().get(0).size());

        StandingsDto standingsDto = leagueDto.getStandings().get(0).get(0);
        Standings standings = league.getStandings().get(0).get(0);
        
        assertEquals(standingsDto.getTeam().getName(), standings.getTeam().getName());
        assertEquals(standingsDto.getGoalsDiff(), standings.getGoalsDiff());
        assertEquals(standingsDto.getForm(), standings.getForm());
        assertEquals(standingsDto.getAll().getPlayed(), standings.getAll().getPlayed());
        assertEquals(standingsDto.getAll().getWin(), standings.getAll().getWin());
        assertEquals(standingsDto.getAll().getLose(), standings.getAll().getLose());
        assertEquals(standingsDto.getAll().getGoals().getGoalsAgainst(), standings.getAll().getGoals().getGoalsAgainst());
        assertEquals(standingsDto.getAll().getGoals().getGoalsFor(), standings.getAll().getGoals().getGoalsFor());
        assertEquals(standingsDto.getAll().getGoals().getGoalsAgainst(), standings.getAll().getGoals().getGoalsAgainst());
        
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
            .all(StatisticsDto.builder().played(10).win(8).draw(1).lose(1)
                .goals(StatisticsDto.Goals.builder().goalsFor(20).goalsAgainst(30).build()).build())
            .home(StatisticsDto.builder().played(5).win(5).draw(0).lose(0)
                .goals(StatisticsDto.Goals.builder().goalsFor(15).goalsAgainst(5).build()).build())
            .away(StatisticsDto.builder().played(5).win(3).draw(1).lose(1)
                .goals(StatisticsDto.Goals.builder().goalsFor(5).goalsAgainst(10).build()).build())
            .build()
        );
        standingsDtos.add(standings);
        return standingsDtos;
    }

}
