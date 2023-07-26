package com.newyeti.apiscraper.domain.port.spi.settings;

import java.util.Optional;

import com.newyeti.apiscraper.domain.model.settings.SettingsModel;

public interface GetSettingsJpaPort {
    Optional<SettingsModel> getSettings(int season);
}
