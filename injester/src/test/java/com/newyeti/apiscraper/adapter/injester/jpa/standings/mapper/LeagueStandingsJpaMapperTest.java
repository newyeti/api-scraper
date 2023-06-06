package com.newyeti.apiscraper.adapter.injester.jpa.standings.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.newyeti.apiscraper.adapter.injester.jpa.standings.entity.LeagueStandingsEntity;
import com.newyeti.apiscraper.adapter.injester.jpa.standings.entity.StandingsEntity;
import com.newyeti.apiscraper.domain.model.avro.schema.Away;
import com.newyeti.apiscraper.domain.model.avro.schema.Goals;
import com.newyeti.apiscraper.domain.model.avro.schema.Home;
import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.model.avro.schema.Standings;
import com.newyeti.apiscraper.domain.model.avro.schema.Team;

@SpringBootTest
@ActiveProfiles("test")
public class LeagueStandingsJpaMapperTest {
    
    @Autowired
    private LeagueStandingsJpaMapper leagueStandingsJpaMapper;

    @Test
    public void givenLeagueDomainModel_whenMappedToEntity_thenCorrect() {
        League league = getLeague();
        LeagueStandingsEntity entity = leagueStandingsJpaMapper.toLeagueStandingsEntity(league);
        assertEquals(1, entity.getLeagueId());
        assertEquals("Premier League", entity.getName());
        assertEquals(2023, entity.getSeason());
        assertEquals("England Flag", entity.getFlag());
        assertEquals("England Logo", entity.getLogo());
        assertEquals(league.getStandings().size(), entity.getStandings().size());

        Standings standingsDomain = league.getStandings().get(0);
        StandingsEntity standingsEntity = entity.getStandings().get(0);

        assertEquals(standingsDomain.getPoints(), standingsEntity.getPoints());
        assertEquals(standingsDomain.getGoalsDiff(), standingsEntity.getGoalsDiff());
        assertEquals(standingsDomain.getGroup(), standingsEntity.getGroup());
        assertEquals(standingsDomain.getTeam().getId(), standingsEntity.getTeam().getId());
        assertEquals(standingsDomain.getTeam().getName(), standingsEntity.getTeam().getName());
        assertEquals(standingsDomain.getHome().getPlayed(), standingsEntity.getHome().getPlayed());
        assertEquals(standingsDomain.getHome().getWin(), standingsEntity.getHome().getWin());
        assertEquals(standingsDomain.getHome().getDraw(), standingsEntity.getHome().getDraw());
        assertEquals(standingsDomain.getHome().getLose(), standingsEntity.getHome().getLose());
        assertEquals(standingsDomain.getHome().getGoals().getGoalsFor(), standingsEntity.getHome().getGoals().getGoalsFor());
        assertEquals(standingsDomain.getHome().getGoals().getGoalsAgainst(), standingsEntity.getHome().getGoals().getGoalsAgainst());
        assertEquals(standingsDomain.getAway().getPlayed(), standingsEntity.getAway().getPlayed());
        assertEquals(standingsDomain.getAway().getWin(), standingsEntity.getAway().getWin());
        assertEquals(standingsDomain.getAway().getDraw(), standingsEntity.getAway().getDraw());
        assertEquals(standingsDomain.getAway().getLose(), standingsEntity.getAway().getLose());
        assertEquals(standingsDomain.getAway().getGoals().getGoalsFor(), standingsEntity.getAway().getGoals().getGoalsFor());
        assertEquals(standingsDomain.getAway().getGoals().getGoalsAgainst(), standingsEntity.getAway().getGoals().getGoalsAgainst());

    }

    private League getLeague() {
        Standings manUtdStandings = Standings.newBuilder()
            .setPoints(50)
            .setGoalsDiff(40)
            .setGroup("PL")
            .setTeam(Team.newBuilder()
                .setId(30)
                .setName("ManUtd")
                .setLogo("ManUtd Logo")
                .build())
            .setHome(Home.newBuilder()
                .setPlayed(17)
                .setWin(10)
                .setDraw(5)
                .setLose(2)
                .setGoals(Goals.newBuilder()
                    .setGoalsFor(88)
                    .setGoalsAgainst(30)
                    .build())
                .build())
            .setAway(Away.newBuilder()
                .setPlayed(6)
                .setWin(4)
                .setDraw(1)
                .setLose(1)
                .setGoals(Goals.newBuilder()
                    .setGoalsFor(29)
                    .setGoalsAgainst(21)
                    .build())
                .build())
            .build();

        List<Standings> standList = new ArrayList<>();
        standList.add(manUtdStandings);

        return League.newBuilder()
            .setId(1)
            .setName("Premier League")
            .setSeason(2023)
            .setCountry("England")
            .setFlag("England Flag")
            .setLogo("England Logo")
            .setStandings(standList)
            .build();
    }

}
