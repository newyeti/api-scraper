package com.newyeti.apiscraper.infrastructure.jpa.mongo.standings;

import java.util.ArrayList;
import java.util.List;

import com.newyeti.apiscraper.domain.model.avro.schema.Away;
import com.newyeti.apiscraper.domain.model.avro.schema.Goals;
import com.newyeti.apiscraper.domain.model.avro.schema.Home;
import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.model.avro.schema.Standings;
import com.newyeti.apiscraper.domain.model.avro.schema.Team;

public class StandingsDataConfig {

    public static League getLeague(int id, int season){
        return League.newBuilder()
            .setId(id)
            .setName("Premier League")
            .setSeason(season)
            .setCountry("England")
            .setFlag("England Flag")
            .setLogo("England Logo")
            .setStandings(getStandingsList())
            .build();
    }

    public static List<Standings> getStandingsList() {
       List<Standings> teamStandings = new ArrayList<>();
       teamStandings.add(getStandings("ManUtd"));
       return teamStandings;
    }

    public static Standings getStandings(String teamName) {
        return  Standings.newBuilder()
                    .setPoints(50)
                    .setGoalsDiff(40)
                    .setGroup("PL")
                    .setTeam(Team.newBuilder()
                        .setId(30)
                        .setName(teamName)
                        .setLogo("Team Logo")
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
    }

}
