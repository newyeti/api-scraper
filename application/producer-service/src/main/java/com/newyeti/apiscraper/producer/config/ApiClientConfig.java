package com.newyeti.apiscraper.producer.config;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.newyeti.apiscraper.producer.handler.http.ApiKeyStruct;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "football-api")
@Data
public class ApiClientConfig {
    
    @NotBlank
    private String baseUrl;

    @NotBlank
    private String apiHost;

    @NotEmpty
    private String apiKeys;

    @Value("${maxRequestPerDay:100}")
    private int maxRequestPerDay;

    private static AtomicInteger counter = new AtomicInteger(0);

    public List<String> getApiKeys() {
        return Arrays.asList(apiKeys.split(","));
    }

    @Bean
    public LinkedList<ApiKeyStruct> getApiKeyStructs() {
         List<ApiKeyStruct> mappings =  getApiKeys().stream()
                .map((key) -> ApiKeyStruct.builder()
                .apiKey(key)
                .index(counter.getAndIncrement())
                .maxCalls(maxRequestPerDay)
                .build())
            .collect(Collectors.toList());
        return new LinkedList<>(mappings);
    }
}
