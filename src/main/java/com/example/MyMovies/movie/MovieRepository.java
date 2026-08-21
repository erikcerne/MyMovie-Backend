package com.example.MyMovies.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    boolean existsByTmdbId(long tmdbId);
    Movie findMovieByTmdbId(long tmdbId);
}
