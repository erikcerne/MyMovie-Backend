package com.example.MyMovies;

import com.example.MyMovies.UserRating.UserRatingScervice;
import com.example.MyMovies.dtos.AddRatingDto;
import com.example.MyMovies.tmdb.TmdbClient;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbResponseDto;
import com.example.MyMovies.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    TmdbClient tmdbClient;
    UserService userService;
    UserRatingScervice userRatingScervice;

    public Controller(TmdbClient tmdbClient, UserRatingScervice userRatingScervice, UserService userService) {
        this.tmdbClient = tmdbClient;
        this.userRatingScervice = userRatingScervice;
        this.userService = userService;
    }

    @GetMapping("/trending/movie")
    public ResponseEntity<TmdbResponseDto> trendingMovies() {
        TmdbResponseDto tmdbResponseDto = tmdbClient.trendingMovies();
        return ResponseEntity.ok(tmdbResponseDto);
    }

    @PostMapping("/raiting/add")
    public ResponseEntity<void> addRaiting(@RequestBody AddRatingDto dto){

    }
}