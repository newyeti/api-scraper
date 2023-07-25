package com.newyeti.apiscraper.common.http;

import java.net.URI;
import java.time.LocalDate;
import java.util.function.Function;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.newyeti.apiscraper.common.exception.ApiException;
import com.newyeti.apiscraper.common.exception.ServiceException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HttpClient {

    private final WebClient.Builder webClientBuilder;

    
    public <T> Mono<T> get(Function<UriBuilder, URI> uriFunction, String apiKey, Class<T> classz) {
        return webClientBuilder
                .defaultHeader("X-RapidAPI-Key", apiKey)
                .build()
                .get()
                .uri(uriFunction)
                .exchangeToMono(response -> {
                    ClientResponse.Headers headers = response.headers();
                    System.out.println("Limit:" + headers.header("x-ratelimit-requests-limit"));
                    System.out.println("Remaining:" + headers.header("x-ratelimit-requests-remaining"));
                    
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
