package com.newyeti.apiscraper.infrastructure.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EntityScan("com.newyeti.apiscraper.infrastructure.jpa")
@EnableMongoRepositories("com.newyeti.apiscraper.infrastructure.jpa.mongo")
public class JpaConfig {
    
}
