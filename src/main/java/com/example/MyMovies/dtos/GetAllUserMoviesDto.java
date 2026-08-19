package com.example.MyMovies.dtos;

import com.example.MyMovies.UserMovie.WatchStatus;

import java.time.LocalDate;

public record GetAllUserMoviesDto(String content, int rating, long tmdbId, WatchStatus status, LocalDate date,
                                  String originalTitle,
                                  String posterPath, Double popularity) {
}
