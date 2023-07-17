package com.newyeti.apiscraper.infrastructure.jpa.mongo.settings.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.newyeti.apiscraper.domain.model.settings.SettingsModel;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.settings.entity.SettingsEntity;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.mapper.StructMapperConfig;

@Mapper(config = StructMapperConfig.class)
public interface SettingsJpaMapper {
    
    @Mapping(target = "leagues", 
        expression = "java(java.util.Arrays.asList(settingsEntity.getLeagues().split(\",\")))")
    SettingsModel toSettingsModel(SettingsEntity settingsEntity);

}
