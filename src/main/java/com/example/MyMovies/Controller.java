package com.example.MyMovies;

import com.example.MyMovies.tmdb.TmdbClient;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class Controller {
    TmdbClient tmdbClient;

    public Controller(TmdbClient tmdbClient) {
        this.tmdbClient = tmdbClient;
    }

    @GetMapping("/trending/movie")
    public Mono<TmdbResponseDto> trendingMovies() {
        return tmdbClient.trendingMovies();
    }
}
