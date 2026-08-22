package com.example.MyMovies.dtos;

import com.example.MyMovies.UserMovie.WatchStatus;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbMovieDto;

public record MovieDetailsLogInDto(TmdbMovieDto tmdbMovieDto, WatchStatus watchStatus, String content, Integer rating) {
}
