package com.newyeti.apiscraper.adapter.beakon.rest.standings;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newyeti.apiscraper.adapter.beakon.http.HttpClient;
import com.newyeti.apiscraper.adapter.beakon.rest.exception.ServiceException;
import com.newyeti.apiscraper.adapter.beakon.rest.response.Error;
import com.newyeti.apiscraper.adapter.beakon.rest.response.ErrorResponse;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.ApiResponseDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.RequestDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.ResponseDto;
import com.newyeti.apiscraper.domain.model.avro.schema.League;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper.LeagueStandingsMapper.LEAGUE_STANDING_MAPPER;

import java.util.ArrayList;

@Tag(name="standings", description = "Pull data from api and put in a kafka topic")
@RestController
@RequestMapping("/standings")
@RequiredArgsConstructor
@Slf4j
public class LeagueStandingsController {
    
    private final HttpClient httpClient;

    @PostMapping(value = "/pull", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto pullData(@Valid @RequestBody RequestDto requestDto) throws ServiceException {
        
        ApiResponseDto result = httpClient
            .get(uriBuilder -> uriBuilder
            .path("/standings")
            .queryParam("season", requestDto.getSeason())
            .queryParam("league", requestDto.getLeague())
            .build(), ApiResponseDto.class)
            .block();
            
        if (result != null && !CollectionUtils.isEmpty(result.getResponse())) {
            ApiResponseDto.Response response = result.getResponse().get(0);
            League league = LEAGUE_STANDING_MAPPER.toModel(response.getLeague());
            log.info(league.toString());
        } else {
            handleError();
        }

        return ResponseDto.builder()
            .status("success")
            .build();
    }

    private void handleError() throws ServiceException{
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                    .errors(new ArrayList<>())
                                                    .build();
                errorResponse.getErrors().add(Error.builder()
                                    .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                                    .source(new Error.Source("league"))
                                    .reason("request body")
                                    .message("Invalid League Id.")
                                    .build());
                errorResponse.getErrors().add(Error.builder()
                                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                                .source(new Error.Source("season"))
                                .reason("request body")
                                .message("Invalid Season Id.")
                                .build());

        throw new ServiceException(HttpStatus.BAD_REQUEST, errorResponse.getErrors());
    }

}
