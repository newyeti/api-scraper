package com.newyeti.apiscraper.domain.model.settings;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SettingsModel {
    int season;
    List<String> leagues;

}
