package com.example.MyMovies.dtos;

import com.example.MyMovies.UserMovie.WatchStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public record UserMoviesDto(String content, Integer rating, long id, WatchStatus status, LocalDate date,
                            String originalTitle,
                            String posterPath, Double voteAverage, String releaseDate) {
}
