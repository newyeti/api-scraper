package com.newyeti.apiscraper.beakon.adapter.rest.standings;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.newyeti.apiscraper.beakon.adapter.rest.standings.dto.LeagueDto;
import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.api.spi.kafka.AvroProducerPort;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import static com.newyeti.apiscraper.beakon.adapter.rest.standings.mapper.LeagueStandingsMapper.LEAGUE_STANDING_MAPPER;

@Tag(name="standings", description = "Pull data from api and put in a kafka topic")
@RestController
@RequestMapping("standings")
@RequiredArgsConstructor
public class LeagueStandingsController {
    
    //private AvroProducerPort<League> avroProducerPort;
    private WebClient.Builder webClientBuilder;

    @GetMapping
    public void consumeAndSend() {
        LeagueDto leagueDto = webClientBuilder.build()
            .get()
            .uri("https://api-football-v1.p.rapidapi.com/v3/standings?season=2020&league=39")
            .headers(httpHeaders -> {
                httpHeaders.set("X-RapidAPI-Key", "U4y3LniAIdmsh1SryySGibO7k8ELp1syFPvjsnpHOQNWAvpJAk");
                httpHeaders.set("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com");
            })
            .retrieve()
            .bodyToMono(LeagueDto.class)
            .block();

        System.out.println(LEAGUE_STANDING_MAPPER.toDomain(leagueDto));
    }

}
