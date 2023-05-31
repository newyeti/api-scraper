package com.newyeti.apiscraper.adapter.beakon.rest.standings;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newyeti.apiscraper.adapter.beakon.config.AppConfig;
import com.newyeti.apiscraper.adapter.beakon.http.HttpClient;
import com.newyeti.apiscraper.adapter.beakon.rest.exception.ServiceException;
import com.newyeti.apiscraper.adapter.beakon.rest.response.Error;
import com.newyeti.apiscraper.adapter.beakon.rest.response.ErrorResponse;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.ApiResponseDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.RequestDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.ResponseDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper.LeagueMapper;
import com.newyeti.apiscraper.application.kafka.KafkaConfig;
import com.newyeti.apiscraper.application.service.standings.LeagueStandingAppService;
import com.newyeti.apiscraper.domain.model.avro.schema.League;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Tag(name="standings", description = "Pull data from api and put in a kafka topic")
@RestController
@RequestMapping("/standings")
@RequiredArgsConstructor
@Slf4j
public class LeagueStandingsController {
    
    private final HttpClient httpClient;
    private final LeagueMapper leagueMapper;
    private final AppConfig appConfig;
    private final KafkaConfig kafkaConfig;
    private final LeagueStandingAppService leagueStandingsAppService;
    private final ObservationRegistry observationRegistry;

    @PostMapping(value = "/pull", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Observed(name = "controller.standings.pull", 
        contextualName = "pulling-standings-data",
        lowCardinalityKeyValues = {"api", "standings", "action", "pull"})
    public ResponseDto pullData(@Valid @RequestBody RequestDto requestDto) {
        
        ApiResponseDto result = callApi(requestDto);
            
        if (result != null && !CollectionUtils.isEmpty(result.getResponse())) {
            log.info("API Call: GET request=/standings season={} league={} status=SUCCESS", requestDto.getSeason(), requestDto.getLeague());
            ApiResponseDto.Response response = result.getResponse().get(0);
            League league = leagueMapper.toLeague(response.getLeague());

            if(appConfig.isKafkaSendEnabled()) {
                log.debug("Kafka Call: Sending API response to Kafka topic.");
                leagueStandingsAppService.send(kafkaConfig.getStandingsTopic(), league);
            }
        } else {
            log.info("API Call: GET request=/standings season={} league={} status=FAILED", requestDto.getSeason(), requestDto.getLeague());
            handleError();
        }

        return ResponseDto.builder()
            .status("success")
            .build();
    }

    public ApiResponseDto callApi(RequestDto requestDto) {
        return Observation.createNotStarted("league.standings.api", observationRegistry)
            .contextualName("league-standings-external-api-call")
            .observe( () -> {
                 return httpClient
                    .get(uriBuilder -> uriBuilder
                    .path("/standings")
                    .queryParam("season", requestDto.getSeason())
                    .queryParam("league", requestDto.getLeague())
                    .build(), ApiResponseDto.class)
                    .block();
            });
    }

    private void handleError() throws ServiceException{
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                    .errors(new ArrayList<>())
                                                    .build();
                errorResponse.getErrors().add(Error.builder()
                                    .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                                    .source(new Error.Source("league/season"))
                                    .reason("request body")
                                    .message("Invalid season or league.")
                                    .build());
        throw new ServiceException(HttpStatus.BAD_REQUEST, errorResponse.getErrors());
    }

}
