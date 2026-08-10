package com.example.MyMovies.dtos;

import java.util.UUID;

public record AddRatingDto(UUID userId, String comment, int rating, int tmdbId) {
}
