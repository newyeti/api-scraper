package com.newyeti.apiscraper.producer.rest.standings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuccessResponseDto {
    
    private String season;
    private String league;
    
}
