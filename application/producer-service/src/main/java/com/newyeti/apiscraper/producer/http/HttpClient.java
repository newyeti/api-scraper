package com.newyeti.apiscraper.producer.http;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.newyeti.apiscraper.common.exception.ApiException;
import com.newyeti.apiscraper.common.exception.ServiceException;
import com.newyeti.apiscraper.producer.handler.http.ApiKeyHandler;
import com.newyeti.apiscraper.producer.handler.http.ApiKeyStruct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class HttpClient {

    private final WebClient.Builder webClientBuilder;
    private final ApiKeyHandler apiKeyHandler;

    private static final String RAPID_API_KEY = "X-RapidAPI-Key";
    private static final String RATELIMIT_REQUESTS_LIMIT = "x-ratelimit-requests-limit";
    private static final String RATELIMIT_REQUESTS_REMAINING = "x-ratelimit-requests-remaining";

    public <T> Mono<T> get(Function<UriBuilder, URI> uriFunction, ApiKeyStruct apiKeyStruct, Class<T> classz) {
        return webClientBuilder
                .defaultHeader(RAPID_API_KEY, apiKeyStruct.getApiKey())
                .build()
                .get()
                .uri(uriFunction)
                .exchangeToMono(response -> {
                    ClientResponse.Headers headers = response.headers();
                    log.debug("Limit:" + headers.header(RATELIMIT_REQUESTS_LIMIT));
                    log.debug("Remaining:" + headers.header(RATELIMIT_REQUESTS_REMAINING));
                    
                    List<String> requestRemainings =  headers.header(RATELIMIT_REQUESTS_REMAINING);
                    int remaining = CollectionUtils.isEmpty(requestRemainings) ? 0 : 
                        Integer.valueOf(requestRemainings.get(0));
                    
                    synchronized(HttpClient.class){
                        if(remaining <= 0) {
                            log.info("Rate limit reached for api key {}. Limit: {}, Remaining: {}",
                                apiKeyStruct.getApiKey().subSequence(0, 4) + "***",
                                headers.header(RATELIMIT_REQUESTS_LIMIT), 
                                headers.header(RATELIMIT_REQUESTS_REMAINING));      
                    
                            apiKeyHandler.updateApiKey(apiKeyHandler.getDateKey(), apiKeyStruct);
                        }
                    }
                    
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(classz);
                    } else {
                        return switch (response.statusCode().value()) {
                            case 400 -> Mono.error(new ApiException(HttpStatus.BAD_REQUEST, uriFunction.toString(), "bad request made"));
                            case 401, 403 -> Mono.error(new ApiException(HttpStatus.UNAUTHORIZED, uriFunction.toString(), "auth error"));
                            case 404 -> Mono.error(new ApiException(HttpStatus.NOT_FOUND, uriFunction.toString(), "not found"));
                            case 500 -> Mono.error(new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "server error"));
                            default -> Mono.error(new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "something went wrong"));
                        };
                    }
                })
                .onErrorMap(Throwable.class, throwble -> throwble)
                ;
    }

}
