package com.example.MyMovies.tmdb;

import com.example.MyMovies.tmdb.tmdbDtos.TmdbResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class TmdbClient {

    private final RestClient restClient;

    public TmdbClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public TmdbResponseDto trendingMovies() {
        return restClient.get()
                .uri("/trending/movie/week")
                .retrieve()
                .body(TmdbResponseDto.class);
    }
}