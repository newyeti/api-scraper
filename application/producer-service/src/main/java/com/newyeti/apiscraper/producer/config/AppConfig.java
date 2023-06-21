package com.newyeti.apiscraper.producer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class AppConfig {
    
    @Value("${kafka.producer.send.enabled}")
    private boolean isKafkaSendEnabled;
    
}
