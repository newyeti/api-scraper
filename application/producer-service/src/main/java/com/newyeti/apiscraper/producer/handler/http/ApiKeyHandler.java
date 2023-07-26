package com.newyeti.apiscraper.producer.handler.http;

import java.time.LocalDate;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.producer.config.ApiClientConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyHandler {

    private final ApiClientConfig apiClientConfig;

    @Cacheable(value = "rapid-api-key", key = "#date" )
    public ApiKeyStruct getApiKey(String date, int index) {
        return apiClientConfig
                .getApiKeyStructs()
                .get(Integer.valueOf(index));
    }

    public String getCurrentApiKeyIndex(String date) {
        return "0";
    }

    @CachePut(value = "rapid-api-key", key = "#date" )
    public ApiKeyStruct updateApiKey(String date, ApiKeyStruct apiKeyStruct) {
        log.info("Getting new api key for rapid-api-key::{}", date);
        int currentIndex = updateApiKeyIndex(date, apiKeyStruct);
        if(currentIndex < 0) {
            return null;
        }
        return apiClientConfig
            .getApiKeyStructs()
            .get(currentIndex);
    }

    public int updateApiKeyIndex(String date, ApiKeyStruct apiKeyStruct) {
        int currentIndex = Integer.valueOf(apiKeyStruct.getIndex());
        if((currentIndex + 1) >= apiClientConfig.getApiKeyStructs().size()) {
            return -1;
        }
        return currentIndex + 1;
    }

    public String getDateKey() {
        return LocalDate.now().toString();
    }

}
