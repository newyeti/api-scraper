package com.newyeti.apiscraper.producer.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.newyeti.apiscraper.producer.handler.http.ApiKeyHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
    
    private final ApiClientConfig apiClientConfig;
    //private final ApiKeyHandler apiKeyHandler;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient
                .builder()
                .baseUrl(apiClientConfig.getBaseUrl())
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-RapidAPI-Host", apiClientConfig.getApiHost())
                ;
    }
    
}
