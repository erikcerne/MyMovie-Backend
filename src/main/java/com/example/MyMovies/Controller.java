package com.example.MyMovies;

import com.example.MyMovies.UserRating.UserRatingService;
import com.example.MyMovies.tmdb.TmdbClient;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbMovieDto;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbResponseDto;
import com.example.MyMovies.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    TmdbClient tmdbClient;
    UserService userService;
    UserRatingService userRatingScervice;

    public Controller(TmdbClient tmdbClient, UserRatingService userRatingScervice, UserService userService) {
        this.tmdbClient = tmdbClient;
        this.userRatingScervice = userRatingScervice;
        this.userService = userService;
    }

    @GetMapping("/trending/movie")
    public ResponseEntity<TmdbResponseDto> trendingMovies() {
        TmdbResponseDto tmdbResponseDto = tmdbClient.trendingMovies();
        return ResponseEntity.ok(tmdbResponseDto);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<TmdbMovieDto> movieDetails(@PathVariable long id){
        TmdbMovieDto tmdbMovieDto = tmdbClient.tmdbMovieDetails(id);
        return ResponseEntity.ok(tmdbMovieDto);
    }

    @GetMapping("/movie/nowplaying")
    public ResponseEntity<TmdbResponseDto> nowPlayingMovies() {
        TmdbResponseDto tmdbResponseDto = tmdbClient.trendingMovies();
        return ResponseEntity.ok(tmdbResponseDto);
    }
}