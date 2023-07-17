package com.newyeti.apiscraper.domain.services.settings;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.common.exception.ServiceException;
import com.newyeti.apiscraper.domain.model.settings.SettingsModel;
import com.newyeti.apiscraper.domain.port.api.settings.GetSettingsApi;
import com.newyeti.apiscraper.domain.port.spi.settings.GetSettingsJpaPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetSettingsApiService implements GetSettingsApi{

    private final GetSettingsJpaPort getSettingsJpaPort;

    @Override
    public Optional<SettingsModel> getSettings(int season) throws ServiceException {
       return getSettingsJpaPort.getSettings(season);
    }
    
}
