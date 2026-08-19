package com.example.MyMovies.dtos;

import java.util.List;

public record AllUserMoviesDto(List<UserMoviesDto> reviewed, List<UserMoviesDto> watched,
                               List<UserMoviesDto> wantToWatch) {
}