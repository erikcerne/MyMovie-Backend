package com.example.MyMovies.tmdb;

import com.example.MyMovies.tmdb.tmdbDtos.TmdbResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class TmdbClient {

    WebClient webClient;

    public TmdbClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<TmdbResponseDto> trendingMovies() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/trending/movie/week").build())
                .retrieve()
                .bodyToMono(TmdbResponseDto.class);
    }
}
