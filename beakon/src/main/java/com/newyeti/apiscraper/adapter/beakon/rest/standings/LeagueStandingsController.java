package com.newyeti.apiscraper.adapter.beakon.rest.standings;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.LeagueDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper.LeagueStandingsMapper.LEAGUE_STANDING_MAPPER;

@Tag(name="standings", description = "Pull data from api and put in a kafka topic")
@RestController
@RequestMapping("/standings")
@RequiredArgsConstructor
@Slf4j
public class LeagueStandingsController {
    
    private final WebClient.Builder webClientBuilder;

    @PostMapping("/{season}/{league}")
    @ResponseStatus(HttpStatus.OK)
    public void pullData(@RequestHeader MultiValueMap<String, String> httpHeaders, @PathVariable String season, @PathVariable String league) {
        String result = webClientBuilder.build()
            .get()
            .uri(uriBuilder -> uriBuilder
                .path("/standings")
                .queryParam("season", season)
                .queryParam("league", league)
                .build())
            .retrieve()
            .bodyToMono(String.class)
            .block();
        
        log.info(result);
        // System.out.println(LEAGUE_STANDING_MAPPER.toDomain(leagueDto));
    }

}
