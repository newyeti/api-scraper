package com.newyeti.apiscraper.application.rest.standings.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDto {
    @NotBlank
    private String season;
    @NotBlank
    private String league;
}
