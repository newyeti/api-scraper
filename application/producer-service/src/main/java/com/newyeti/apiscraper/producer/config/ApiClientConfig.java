package com.newyeti.apiscraper.producer.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
    private List<String> apiKeys;

}
