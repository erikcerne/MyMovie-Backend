package com.example.MyMovies.dtos;

import com.example.MyMovies.UserMovie.WatchStatus;

import java.time.LocalDate;

public record UserMoviesDto(String content, Integer rating, long tmdbId, WatchStatus status, LocalDate date,
                            String originalTitle,
                            String posterPath, Double popularity, String releaseDate) {
}
