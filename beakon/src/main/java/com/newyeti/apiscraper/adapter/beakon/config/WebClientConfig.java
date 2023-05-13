package com.newyeti.apiscraper.adapter.beakon.config;

import java.net.URI;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
    
    private final ApiClientConfig apiClientConfig;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient
                .builder()
                .baseUrl(apiClientConfig.getBaseUrl())
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-RapidAPI-Key", apiClientConfig.getApiKeys().get(0))
                .defaultHeader("X-RapidAPI-Host", apiClientConfig.getApiHost())
                ;
    }

}
