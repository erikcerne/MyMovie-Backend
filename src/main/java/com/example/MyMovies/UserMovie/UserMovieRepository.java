package com.example.MyMovies.UserMovie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserMovieRepository extends JpaRepository<UserMovie, UUID> {
    List<UserMovie> findAllByUser_UserId(String userUserId);

    boolean existsUserMovieByUser_UserIdAndMovie_TmdbId(String userUserId, long movieTmdbId);
}