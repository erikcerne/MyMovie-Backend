package com.example.MyMovies.tmdb.tmdbDtos;

import java.util.List;

public record TmdbResponseDto(List<TmdbMovieDto> results) {
}
