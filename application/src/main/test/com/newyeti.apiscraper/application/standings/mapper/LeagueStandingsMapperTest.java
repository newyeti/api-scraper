package com.newyeti.apiscraper.application.standings.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.LeagueStandingsDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.StandingsDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.StatisticsDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.TeamDto;
import com.newyeti.apiscraper.application.rest.standings.mapper.LeagueStandingsMapper;
import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;
import com.newyeti.apiscraper.domain.model.avro.schema.Standings;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@ComponentScan(basePackageClasses = leagueStandingsMapper.class)
@ActiveProfiles("test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class leagueStandingsMapperTest {

    @Autowired
    private LeagueStandingsMapper leagueStandingsMapper;
    
    @Test
    public void givenLeagueStandingsDto_whenMapToLeagueStandings_thenCorrect() {
        LeagueStandingsDto leagueStandingsDto = getLeagueStandingsDto();
        LeagueStandings leagueStandings = leagueStandingsMapper.toLeague(leagueDto);

        assertNotNull(league, "league object should not be null");
        assertEquals(leagueDto.getId(), league.getId());
        assertEquals(leagueDto.getName(), league.getName());
        assertEquals(leagueDto.getCountry(), league.getCountry());
        assertEquals(leagueDto.getFlag(), league.getFlag());   
    }

    @Test
    public void givenStandingsDtoList_whenMapToStandingListModel_thenCorrect() {
        LeagueStandingsDto leagueStandingsDto = getLeagueStandingsDto();
        LeagueStandings leagueStandings = leagueStandingsMapper.toLeague(leagueStandingsDto);
       
        assertNotNull(league, "league should not be null");
        assertEquals(leagueStandingsDto.getStandings().size(),  league.getStandings().size());
        assertEquals(leagueStandingsDto.getStandings().get(0).size(), league.getStandings().size());

        StandingsDto standingsDto = leagueStandingsDto.getStandings().get(0).get(0);
        Standings standings = league.getStandings().get(0);
        
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

    private LeagueDto getLeagueStandingsDto() {
        return LeagueStandingsDto.builder()
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
