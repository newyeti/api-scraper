package com.newyeti.apiscraper.domain.port.api.settings;

import java.util.Optional;

import com.newyeti.apiscraper.common.exception.ServiceException;
import com.newyeti.apiscraper.domain.model.settings.SettingsModel;

public interface GetSettingsApi {

    Optional<SettingsModel> getSettings(int season) throws ServiceException;

}
