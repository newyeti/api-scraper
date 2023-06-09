package com.newyeti.apiscraper.domain.port.api.standings;

import com.newyeti.apiscraper.common.exception.ServiceException;
import com.newyeti.apiscraper.domain.model.avro.schema.League;

public interface CreateStandginsApi {
    void save(League league, String id, String topic) throws ServiceException;
}
