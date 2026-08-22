package com.example.MyMovies;

import com.example.MyMovies.UserMovie.UserMovieService;
import com.example.MyMovies.UserMovie.WatchStatus;
import com.example.MyMovies.dtos.*;
import com.example.MyMovies.tmdb.TmdbClient;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbMovieDto;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbResponseDto;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbResultsReviewsDto;
import com.example.MyMovies.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieDetailsLogInDto> movieDetailsLogIn(@AuthenticationPrincipal Jwt jwt, @PathVariable long tmdbId) {
        String authId = jwt.getSubject();
        MovieDetailsLogInDto movieDetailsLogInDto = userMovieService.getMovieDetailsLogIn(authId, tmdbId);
        return ResponseEntity.ok(movieDetailsLogInDto);
    }

    @DeleteMapping("/delete/movie/{tmdbId}")
    public ResponseEntity<Void> deleteMovieFromLibrary(@AuthenticationPrincipal Jwt jwt, @PathVariable long tmdbId) {
        String authId = jwt.getSubject();
        userMovieService.deleteMovieFromLibrary(authId, tmdbId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/status/{tmdbId}")
    public ResponseEntity<Void> updateMovieStatus(@AuthenticationPrincipal Jwt jwt, @PathVariable long tmdbId, @RequestBody WatchStatus status) {
        String authId = jwt.getSubject();
        userMovieService.updateStatus(authId, tmdbId, status);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/review")
    public ResponseEntity<Void> updateReview(@AuthenticationPrincipal Jwt jwt, @RequestBody AddRatingDto addRatingDto) {
        String authId = jwt.getSubject();
        userMovieService.updateReview(authId, addRatingDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/me/movies")
    public ResponseEntity<AllUserMoviesDto> getAllRatingsForUser(@AuthenticationPrincipal Jwt jwt) {
        String authId = jwt.getSubject();
        AllUserMoviesDto allUserMoviesDto = userMovieService.getAllRatings(authId);
        return ResponseEntity.ok(allUserMoviesDto);
    }

    @PostMapping("/users/me/movies/reviews")
    public ResponseEntity<Void> addReview(@AuthenticationPrincipal Jwt jwt, @RequestBody AddRatingDto addRatingDto) {
        String authId = jwt.getSubject();
        userMovieService.addRevewe(authId, addRatingDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/me/movies/library")
    public ResponseEntity<Void> addToLibrary(@AuthenticationPrincipal Jwt jwt, @RequestBody AddToLibraryDto addToLibraryDto) {
        String authId = jwt.getSubject();
        userMovieService.addWatch(authId, addToLibraryDto.tmdbId(), addToLibraryDto.watchStatus());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users")
    public ResponseEntity<Void> register(@AuthenticationPrincipal Jwt jwt, @RequestBody RegisterUserDto registerUserDto) {
        String authId = jwt.getSubject();
        userService.registerUser(authId, registerUserDto.name());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/me/exists")
    public ResponseEntity<Boolean> isExistingUser(@AuthenticationPrincipal Jwt jwt) {
        String authId = jwt.getSubject();
        return ResponseEntity.ok(userService.isExistingUser(authId));
    }

    @GetMapping("/movies/upcoming")
    public ResponseEntity<TmdbResponseDto> upcomingMovies() {
        TmdbResponseDto tmdbResponseDto = tmdbClient.upcomingMovies();
        return ResponseEntity.ok(tmdbResponseDto);
    }

    @GetMapping("/movies/trending")
    public ResponseEntity<TmdbResponseDto> trendingMovies() {
        TmdbResponseDto tmdbResponseDto = tmdbClient.trendingMovies();
        return ResponseEntity.ok(tmdbResponseDto);
    }

    @GetMapping("/movies/top-rated")
    public ResponseEntity<TmdbResponseDto> topRatedMovies() {
        TmdbResponseDto tmdbResponseDto = tmdbClient.topRatedMovies();
        return ResponseEntity.ok(tmdbResponseDto);
    }

    @GetMapping("/movies/now-playing")
    public ResponseEntity<TmdbResponseDto> nowPlayingMovies() {
        TmdbResponseDto tmdbResponseDto = tmdbClient.nowPlayingMovies();
        return ResponseEntity.ok(tmdbResponseDto);
    }

    @GetMapping("/movies/popular")
    public ResponseEntity<TmdbResponseDto> popularMovies() {
        TmdbResponseDto tmdbResponseDto = tmdbClient.popularMovies();
        return ResponseEntity.ok(tmdbResponseDto);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<TmdbMovieDto> movieDetails(@PathVariable long id) {
        TmdbMovieDto tmdbMovieDto = tmdbClient.tmdbMovieDetails(id);
        return ResponseEntity.ok(tmdbMovieDto);
    }

    @GetMapping("/movies/{id}/similar")
    public ResponseEntity<TmdbResponseDto> similarMoviesById(@PathVariable long id) {
        TmdbResponseDto tmdbResponseDto = tmdbClient.similarMoviesById(id);
        return ResponseEntity.ok(tmdbResponseDto);
    }

    @GetMapping("/movies/{id}/reviews")
    public ResponseEntity<TmdbResultsReviewsDto> reviews(@PathVariable long id) {
        TmdbResultsReviewsDto tmdbResultsReviewsDto = tmdbClient.reviewsForMovie(id);
        return ResponseEntity.ok(tmdbResultsReviewsDto);
    }
}