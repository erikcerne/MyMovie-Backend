package com.example.MyMovies.dtos;

import com.example.MyMovies.UserMovie.WatchStatus;

public record AddToLibraryDto(WatchStatus watchStatus, long tmdbId) {
}
