package com.example.MyMovies.tmdb.tmdbDtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TmdbReviewsDto(String content, String createdAt, AuthorDetails authorDetails) {
}
