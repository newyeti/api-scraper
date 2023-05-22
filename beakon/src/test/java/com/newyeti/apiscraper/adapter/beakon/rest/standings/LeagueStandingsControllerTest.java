package com.newyeti.apiscraper.adapter.beakon.rest.standings;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newyeti.apiscraper.adapter.beakon.config.ApiClientConfig;
import com.newyeti.apiscraper.adapter.beakon.http.HttpClient;
import com.newyeti.apiscraper.adapter.beakon.rest.exception.ApiException;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.ApiResponseDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.LeagueDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.RequestDto;
import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.ApiResponseDto.Response;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = LeagueStandingsController.class)
@ActiveProfiles("test")
public class LeagueStandingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private HttpClient httpClient;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ObjectMapper objectMapper;

    private static MockWebServer mockWebServer;

    private String mockResponseBody = "{\"get\":\"standings\",\"parameters\":{\"league\":\"39\",\"season\":\"2020\"},\"errors\":[],\"results\":1,\"paging\":{\"current\":1,\"total\":1},\"response\":[{\"league\":{\"id\":39,\"name\":\"PremierLeague\",\"country\":\"England\",\"logo\":\"https://media.api-sports.io/football/leagues/39.png\",\"flag\":\"https://media.api-sports.io/flags/gb.svg\",\"season\":2020,\"standings\":[[{\"rank\":1,\"team\":{\"id\":50,\"name\":\"ManchesterCity\",\"logo\":\"https://media.api-sports.io/football/teams/50.png\"},\"points\":74,\"goalsDiff\":45,\"group\":\"PremierLeague\",\"form\":\"WWWLW\",\"status\":\"same\",\"description\":\"Promotion-ChampionsLeague(GroupStage)\",\"all\":{\"played\":31,\"win\":23,\"draw\":5,\"lose\":3,\"goals\":{\"for\":66,\"against\":21}},\"home\":{\"played\":16,\"win\":12,\"draw\":2,\"lose\":2,\"goals\":{\"for\":36,\"against\":13}},\"away\":{\"played\":15,\"win\":11,\"draw\":3,\"lose\":1,\"goals\":{\"for\":30,\"against\":8}},\"update\":\"2021-04-05T00:00:00+00:00\"},{\"rank\":20,\"team\":{\"id\":62,\"name\":\"SheffieldUtd\",\"logo\":\"https://media.api-sports.io/football/teams/62.png\"},\"points\":14,\"goalsDiff\":-35,\"group\":\"PremierLeague\",\"form\":\"LLLWL\",\"status\":\"same\",\"description\":\"Relegation-Championship\",\"all\":{\"played\":30,\"win\":4,\"draw\":2,\"lose\":24,\"goals\":{\"for\":17,\"against\":52}},\"home\":{\"played\":15,\"win\":3,\"draw\":1,\"lose\":11,\"goals\":{\"for\":10,\"against\":22}},\"away\":{\"played\":15,\"win\":1,\"draw\":1,\"lose\":13,\"goals\":{\"for\":7,\"against\":30}},\"update\":\"2021-04-05T00:00:00+00:00\"}]]}}]}";

    @BeforeEach
    public void setupMockServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        webClientBuilder.baseUrl(String.format("http://localhost:%s", mockWebServer.getPort()));
    }

    @AfterAll
    public static void tearDown() throws IOException{
        mockWebServer.shutdown();
    }

    private RequestDto getRequestDto() {
        return RequestDto.builder().league("39").season("2020").build();
    }

    private ApiResponseDto getApiResponseDto() {
        LeagueDto leagueDto = LeagueDto.builder().build();
        Response response = Response.builder().league(leagueDto).build();
        List<Response> responses = new ArrayList<>();
        responses.add(response); 
        return ApiResponseDto.builder()
                            .response(responses)
                            .build();
    }

    @Test
    public void whenValidInput_thenReturn200() throws Exception {
        mockWebServer.enqueue(new MockResponse()
            .setResponseCode(200)
            .setHeader("content-type", "application/json")
            .setBody(mockResponseBody)
        );

        mockMvc.perform(post("/standings/pull")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(getRequestDto())))
            .andExpect(status().isOk());
    }

    @Test
    public void whenInvalidAPIKey_thenReturn401() throws Exception {
      mockWebServer.enqueue(new MockResponse().setResponseCode(401));
      mockMvc.perform(post("/standings/pull")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(getRequestDto())))
        .andExpect(status().isUnauthorized());
      
    }

}