package com.newyeti.apiscraper.producer.rest.standings;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newyeti.apiscraper.producer.config.AppConfig;
import com.newyeti.apiscraper.common.http.HttpClient;
import com.newyeti.apiscraper.common.exception.ServiceException;
import com.newyeti.apiscraper.common.error.Error;
import com.newyeti.apiscraper.common.error.ErrorResponse;
import com.newyeti.apiscraper.producer.rest.standings.dto.ApiResponseDto;
import com.newyeti.apiscraper.producer.rest.standings.dto.RequestDto;
import com.newyeti.apiscraper.producer.rest.standings.dto.ResponseDto;
import com.newyeti.apiscraper.producer.rest.standings.dto.SuccessResponseDto;
import com.newyeti.apiscraper.producer.rest.standings.mapper.LeagueStandingsMapper;
import com.newyeti.apiscraper.producer.kafka.KafkaConfig;
import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;
import com.newyeti.apiscraper.domain.model.settings.SettingsModel;
import com.newyeti.apiscraper.domain.port.api.settings.GetSettingsApi;
import com.newyeti.apiscraper.domain.port.api.standings.CreateStandingsApi;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Tag(name="standings", description = "Pull data from api and put in a kafka topic")
@RestController
@RequestMapping("/standings")
@RequiredArgsConstructor
@Slf4j
public class LeagueStandingsController {
    
    private final HttpClient httpClient;
    private final LeagueStandingsMapper leagueStandingsMapper;
    private final AppConfig appConfig;
    private final KafkaConfig kafkaConfig;
    private final ObservationRegistry observationRegistry;
    private final CreateStandingsApi createStandingsApi;
    private final GetSettingsApi getSettingsApi;

    @PostMapping(value = "/pull", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Observed(name = "controller.standings.pull", 
        contextualName = "controller.standings.pull",
        lowCardinalityKeyValues = {"api", "standings", "action", "pull"})
    public ResponseDto pullData(@Valid @RequestBody RequestDto requestDto) {
        ResponseDto responseDto = getResponseDto();

        try{
            SuccessResponseDto successResponseDto = processRequest(requestDto.getSeason(), requestDto.getLeague());
            addSuccessInResponse(successResponseDto, responseDto);
        } catch(ServiceException ex) {
            addErrorsInResponse(ex.getErrors(), responseDto);
        }
        
        return responseDto;
    }

    /**
     * 
     * @param requestDto RequestDto.league is a list of comman separated values
     * @return
     */
    @PostMapping(value = "/{season}/pull", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Observed(name = "controller.standings.pull.season", 
        contextualName = "controller.standings.pull.season",
        lowCardinalityKeyValues = {"api", "standings", "action", "pull"})
    public ResponseEntity<ResponseDto> pullAllData(@PathVariable("season") int season) {
        Optional<SettingsModel> settings = getSettingsApi.getSettings(season);
        ResponseDto responseDto = getResponseDto();
        if(!settings.isPresent()) {
            addErrorInResponse(Error.builder()
                            .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                            .source(new Error.Source("season"))
                            .reason("request body")
                            .message(String.format("Season: %s is not available.",
                                String.valueOf(season)))
                            .build(),
                        responseDto);
        } else {
            SettingsModel settingsModel = settings.get();
            settingsModel.getLeagues().parallelStream()
                .forEach((leagueId) -> {
                    try{
                        SuccessResponseDto successResponseDto = processRequest(String.valueOf(season), leagueId);
                        addSuccessInResponse(successResponseDto, responseDto);
                    } catch(ServiceException ex) {
                        addErrorsInResponse(ex.getErrors(), responseDto);
                    }
                });
        }

        return ResponseEntity.status(
                responseDto.getErrors().size() > 0 ? HttpStatus.BAD_REQUEST : HttpStatus.OK)
            .body(responseDto);
    }

    @WithSpan
    private SuccessResponseDto processRequest(String season, String league) {
        ApiResponseDto result = callRapidApi(season, league);
            
        if (result != null && !CollectionUtils.isEmpty(result.getResponse())) {
            log.info("API Call: POST request=/standings season={} league={} status=SUCCESS", season, league);
            ApiResponseDto.Response response = result.getResponse().get(0);
            LeagueStandings leagueStandings = leagueStandingsMapper.toLeagueStandings(response.getLeagueStandingsDto());

            if(appConfig.isKafkaSendEnabled()) {
                log.info("Kafka Call: Sending API response to Kafka topic.");
                createStandingsApi.send(leagueStandings, kafkaConfig.getStandingsTopic(), 
                    UUID.randomUUID().toString());
            } else {
                log.info("Kafka not enabled. Saving in database.");
                createStandingsApi.create(UUID.randomUUID().toString(), leagueStandings);
            }
        } else {
            log.error("API Call: POST request=/standings season={} league={} status=FAILED", season, league);
            ErrorResponse errorResponse = initErrorResponse();
            addError(Error.builder()
                            .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                            .source(new Error.Source("league/season"))
                            .reason("request")
                            .message(String.format("Invalid season: %s or league:%s.",
                                season, league))
                            .build(),
                        errorResponse);
            throw new ServiceException(HttpStatus.BAD_REQUEST, errorResponse.getErrors());
        }

        return SuccessResponseDto.builder()
            .league(league)
            .season(season)
            .build();

    }

    @WithSpan
    private ApiResponseDto callRapidApi(String season, String league) {
        return Observation.createNotStarted("league.standings.api", observationRegistry)
            .contextualName("league-standings-external-api-call")
            .observe( () -> {
                 return httpClient
                    .get(uriBuilder -> uriBuilder
                    .path("/standings")
                    .queryParam("season", season)
                    .queryParam("league", league)
                    .build(), ApiResponseDto.class)
                    .block();
            });
    }

    @WithSpan
    private void addError(Error error, ErrorResponse errorResponse) {
            errorResponse.getErrors().add(error);
    }

    @WithSpan
    private void addErrorInResponse(Error error, ResponseDto responseDto) {
        initErrorsInReponse(responseDto);

        synchronized(this){
            responseDto.getErrors().add(error);
        }
    }

    @WithSpan
    private void addErrorsInResponse(List<Error> errors, ResponseDto responseDto) {
        initErrorsInReponse(responseDto);
        
        synchronized(this){
            responseDto.getErrors().addAll(errors);
        }
    }

    private void initErrorsInReponse(ResponseDto responseDto) {
        if(CollectionUtils.isEmpty(responseDto.getErrors())) {
            responseDto.setErrors(new ArrayList<>());
        }
    }

    @WithSpan
    private void addSuccessInResponse(SuccessResponseDto successResponseDto, ResponseDto responseDto){
        if(CollectionUtils.isEmpty(responseDto.getSuccess())) {
            responseDto.setSuccess(new ArrayList<>());
        }

        synchronized(this){
            responseDto.getSuccess().add(successResponseDto);
        }
    }

    private ErrorResponse initErrorResponse() {
        return ErrorResponse.builder()
                            .errors(new ArrayList<>())
                            .build();
    }

    private ResponseDto getResponseDto() {
        return ResponseDto.builder()
            .build();
    }

}
