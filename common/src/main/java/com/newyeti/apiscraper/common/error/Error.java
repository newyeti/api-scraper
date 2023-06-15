package com.newyeti.apiscraper.common.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {
    
    private String code;
    private Source source;
    private String reason;
    private String message;

    @Data
    @AllArgsConstructor
    public static class Source {
        private String pointer;
    }
    
}
