package com.newyeti.apiscraper.adapter.injester.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EntityScan("com.newyeti.apiscraper.adapter.injester.jpa")
@EnableMongoRepositories("com.newyeti.apiscraper.adapter.injester.jpa")
public class JpaConfig {
    
}
