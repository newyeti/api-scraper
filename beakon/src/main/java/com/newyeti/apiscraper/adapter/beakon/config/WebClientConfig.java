package com.newyeti.apiscraper.adapter.beakon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction;

    @Bean
    public WebClient.Builder webClientBuilder(LoadBalancerClient loadBalancerClient) {
        return WebClient
                .builder()
                .filter(loadBalancerExchangeFilterFunction)
                .baseUrl("https://api-football-v1.p.rapidapi.com/v3");
    }

}
