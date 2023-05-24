package com.newyeti.apiscraper.adapter.beakon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class AppConfig {
    
    @Value("${app.kafka.send.enabled}")
    private boolean isKafkaSendEnabled;

}
