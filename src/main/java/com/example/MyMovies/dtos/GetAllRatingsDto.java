package com.example.MyMovies.dtos;

import java.time.LocalDate;

public record GetAllRatingsDto(String content, int rating, long tmdbId, LocalDate date, Long id, String originalTitle,
                               String posterPath, Double popularity) {
}
