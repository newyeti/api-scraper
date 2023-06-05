package com.newyeti.apiscraper.adapter.beakon.http;

import java.net.URI;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.newyeti.apiscraper.adapter.beakon.rest.exception.ApiException;
import com.newyeti.apiscraper.adapter.beakon.rest.exception.ServiceException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HttpClient {

    private final WebClient.Builder webClientBuilder;
    
    public <T> Mono<T> get(Function<UriBuilder, URI> uriFunction, Class<T> classz) {
        return webClientBuilder
                .build()
                .get()
                .uri(uriFunction)
                .retrieve()
                .onStatus((status) -> status.isError(), 
                    response -> switch (response.statusCode().value()) {
                        case 400 -> Mono.error(new ApiException(HttpStatus.BAD_REQUEST, uriFunction.toString(), "bad request made"));
                        case 401, 403 -> Mono.error(new ApiException(HttpStatus.UNAUTHORIZED, uriFunction.toString(), "auth error"));
                        case 404 -> Mono.error(new ApiException(HttpStatus.NOT_FOUND, uriFunction.toString(), "not found"));
                        case 500 -> Mono.error(new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "server error"));
                        default -> Mono.error(new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "something went wrong"));
                    })
                .bodyToMono(classz)
                .onErrorMap(Throwable.class, throwble -> throwble)
                ;
    }

}
