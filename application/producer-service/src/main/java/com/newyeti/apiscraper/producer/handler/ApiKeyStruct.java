package com.newyeti.apiscraper.producer.handler;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Builder;


@Data
@RequiredArgsConstructor
@Builder
public class ApiKeyStruct {
    final int index;
    final String apiKey;
    final int maxCalls;
}
