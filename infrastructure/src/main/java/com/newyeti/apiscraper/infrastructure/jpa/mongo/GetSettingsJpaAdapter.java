package com.newyeti.apiscraper.infrastructure.jpa.mongo;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.settings.SettingsModel;
import com.newyeti.apiscraper.domain.port.spi.settings.GetSettingsJpaPort;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.settings.entity.SettingsEntity;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.settings.mapper.SettingsJpaMapper;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.settings.repository.SettingsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetSettingsJpaAdapter implements GetSettingsJpaPort{

    private final SettingsRepository settingsRepository;
    private final SettingsJpaMapper settingsJpaMapper;

    @Override
    public Optional<SettingsModel> getSettings(int season) {
       Optional<SettingsEntity> settingsEntity = settingsRepository.findBySeason(season);
       if (settingsEntity.isPresent()) {
            return Optional.of(settingsJpaMapper.toSettingsModel(settingsEntity.get()));
       }
       return Optional.empty();
    }
    
}
