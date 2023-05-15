package com.newyeti.apiscraper.adapter.beakon.rest.standings;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newyeti.apiscraper.adapter.beakon.rest.http.HttpClient;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.ApiResponseDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.RequestDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.ResponseDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//import static com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper.LeagueStandingsMapper.LEAGUE_STANDING_MAPPER;

@Tag(name="standings", description = "Pull data from api and put in a kafka topic")
@RestController
@RequestMapping("/standings")
@RequiredArgsConstructor
@Slf4j
public class LeagueStandingsController {
    
    private final HttpClient httpClient;

    @PostMapping(value = "/pull", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto pullData(@Valid @RequestBody RequestDto requestDto) {
        
        ApiResponseDto result = httpClient
            .get(uriBuilder -> uriBuilder
            .path("/standings")
            .queryParam("season", requestDto.getSeason())
            .queryParam("league", requestDto.getLeague())
            .build(), ApiResponseDto.class)
            .block();
            
        log.info(result.toString());
        // System.out.println(LEAGUE_STANDING_MAPPER.toDomain(leagueDto));
        return ResponseDto.builder()
            .status("success")
            .build();
    }

}
