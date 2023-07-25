package com.newyeti.apiscraper.producer.handler;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.producer.config.ApiClientConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiKeyHandler {

    private final ApiClientConfig apiClientConfig;

    @Cacheable(value = "apikey", key = "#date" )
    public String getApiKey(String date) {
        return apiClientConfig.getApiKeys().get(0);
    }

}
