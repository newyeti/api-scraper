package com.newyeti.apiscraper.adapter.beakon.rest.http;

import java.net.URI;
import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import jakarta.ws.rs.BadRequestException;
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
                        case 400 -> Mono.error(new BadRequestException("bad request made"));
                        case 401, 403 -> Mono.error(new Exception("auth error"));
                        case 404 -> Mono.error(new Exception("Maybe not an error?"));
                        case 500 -> Mono.error(new Exception("server error"));
                        default -> Mono.error(new Exception("something went wrong"));
                    })
                .bodyToMono(classz)
                .onErrorMap(Throwable.class, throwble -> new Exception("plain exception"))
                ;
    }

}
