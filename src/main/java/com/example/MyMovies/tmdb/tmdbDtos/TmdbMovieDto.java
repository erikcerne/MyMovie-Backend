package com.example.MyMovies.tmdb.tmdbDtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TmdbMovieDto(Long id, String originalTitle, String overview, String backdropPath, String posterPath,
                           String releaseDate, Double popularity, Double voteAverage, List<Integer> genreIds) {
}