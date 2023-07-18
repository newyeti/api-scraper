package com.newyeti.apiscraper.infrastructure.jpa.mongo.settings.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.newyeti.apiscraper.infrastructure.jpa.mongo.settings.entity.SettingsEntity;

public interface SettingsRepository extends MongoRepository<SettingsEntity, String> {
    Optional<SettingsEntity> findBySeason(int season);
}
