package com.example.MyMovies.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AllUserMoviesDto(List<UserMoviesDto> reviewed, List<UserMoviesDto> watched,
                               List<UserMoviesDto> wantToWatch) {
}