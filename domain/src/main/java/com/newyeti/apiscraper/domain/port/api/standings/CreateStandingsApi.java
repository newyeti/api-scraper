package com.newyeti.apiscraper.domain.port.api.standings;

import com.newyeti.apiscraper.common.exception.ServiceException;
import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.api.BusinessServiceApi;

public interface CreateStandingsApi extends BusinessServiceApi{
    void create(League league, String id, String topic) throws ServiceException;
}
