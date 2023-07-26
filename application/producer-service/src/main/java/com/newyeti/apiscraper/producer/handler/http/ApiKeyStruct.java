package com.newyeti.apiscraper.producer.handler.http;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

import lombok.Builder;


@Data
@RequiredArgsConstructor
@Builder
public class ApiKeyStruct implements Serializable{
    final int index;
    final String apiKey;
    final int maxCalls;
}
