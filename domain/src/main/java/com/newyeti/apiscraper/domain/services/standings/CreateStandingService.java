package com.newyeti.apiscraper.domain.services.standings;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.api.standings.CreateStandginsApi;

public class CreateStandingService implements CreateStandginsApi {

    @Override
    public void save(League league) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    
}
