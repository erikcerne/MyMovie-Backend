package com.example.MyMovies;

import com.example.MyMovies.UserMovie.UserMovieService;
import com.example.MyMovies.dtos.AddRatingDto;
import com.example.MyMovies.dtos.GetAllRatingsDto;
import com.example.MyMovies.tmdb.TmdbClient;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbMovieDto;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbResponseDto;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbResultsReviewsDto;
import com.example.MyMovies.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
@RequestMapping("/api")
public class Controller {

    TmdbClient tmdbClient;
    UserService userService;
    UserMovieService userMovieService;

    public Controller(TmdbClient tmdbClient, UserMovieService userMovieService, UserService userService) {
        this.tmdbClient = tmdbClient;
        this.userMovieService = userMovieService;
        this.userService = userService;
    }

    @GetMapping("/revewe/all")
    public ResponseEntity<List<GetAllRatingsDto>> getAllRatingsForUser(@AuthenticationPrincipal Jwt jwt) {
        String authId = jwt.getSubject();
        List<GetAllRatingsDto> getAllRatingsDtos = userMovieService.getAllRatings(authId);
        return ResponseEntity.ok(getAllRatingsDtos);
    }

    @PostMapping("/revewe")
    public ResponseEntity<Void> addRevewe(@AuthenticationPrincipal Jwt jwt, @RequestBody AddRatingDto addRatingDto) {
        String authId = jwt.getSubject();
        userMovieService.addRevewe(authId, addRatingDto);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@AuthenticationPrincipal Jwt jwt, @RequestBody String name) {
        String authId = jwt.getSubject();
        userService.registerUser(authId, name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/isExistingUser")
    public ResponseEntity<Boolean> isExistingUser(@AuthenticationPrincipal Jwt jwt) {
        String authId = jwt.getSubject();
        return ResponseEntity.ok(userService.isExistingUser(authId));
    }

    @GetMapping("/movie/upcoming")
    public ResponseEntity<TmdbResponseDto> upcomingMovies() {
        TmdbResponseDto tmdbResponseDto = tmdbClient.upcomingMovies();
        return ResponseEntity.ok(tmdbResponseDto);
    }

    @GetMapping("/movie/trending")
    public ResponseEntity<TmdbResponseDto> trendingMovies() {
        TmdbResponseDto tmdbResponseDto = tmdbClient.trendingMovies();
        return ResponseEntity.ok(tmdbResponseDto);
    }

    @GetMapping("/movie/top_rated")
    public ResponseEntity<TmdbResponseDto> topRatedMovies() {
        TmdbResponseDto tmdbResponseDto = tmdbClient.topRatedMovies();
        return ResponseEntity.ok(tmdbResponseDto);
    }

    @GetMapping("/movie/nowplaying")
    public ResponseEntity<TmdbResponseDto> nowPlayingMovies() {
        TmdbResponseDto tmdbResponseDto = tmdbClient.nowPlayingMovies();
        return ResponseEntity.ok(tmdbResponseDto);
    }

    @GetMapping("/movie/popular")
    public ResponseEntity<TmdbResponseDto> popularMovies() {
        TmdbResponseDto tmdbResponseDto = tmdbClient.popularMovies();
        return ResponseEntity.ok(tmdbResponseDto);
    }

    @GetMapping("/movie/{id}/Details")
    public ResponseEntity<TmdbMovieDto> movieDetails(@PathVariable long id) {
        TmdbMovieDto tmdbMovieDto = tmdbClient.tmdbMovieDetails(id);
        return ResponseEntity.ok(tmdbMovieDto);
    }

    @GetMapping("/movie/{id}/similar")
    public ResponseEntity<TmdbResponseDto> similarMoviesById(@PathVariable long id) {
        TmdbResponseDto tmdbResponseDto = tmdbClient.similarMoviesById(id);
        return ResponseEntity.ok(tmdbResponseDto);
    }

    @GetMapping("/movie/{id}/reviews")
    public ResponseEntity<TmdbResultsReviewsDto> reviews(@PathVariable long id) {
        TmdbResultsReviewsDto tmdbResultsReviewsDto = tmdbClient.reviewsForMovie(id);
        return ResponseEntity.ok(tmdbResultsReviewsDto);
    }
}