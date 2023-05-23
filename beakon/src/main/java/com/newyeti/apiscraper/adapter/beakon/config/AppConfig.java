package com.newyeti.apiscraper.adapter.beakon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig {
    
    @Value("${kafka.send.enabled:false}")
    private boolean isKafkaSendEnabled;

}
