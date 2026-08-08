package com.example.MyMovies;

import com.example.MyMovies.tmdb.TmdbClient;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    private final TmdbClient tmdbClient;

    public Controller(TmdbClient tmdbClient) {
        this.tmdbClient = tmdbClient;
    }

    @GetMapping("/trending/movie")
    public ResponseEntity<TmdbResponseDto> trendingMovies() {
        TmdbResponseDto tmdbResponseDto = tmdbClient.trendingMovies();
        return ResponseEntity.ok(tmdbResponseDto);
    }
}