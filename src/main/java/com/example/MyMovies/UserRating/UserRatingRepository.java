package com.example.MyMovies.UserRating;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRatingRepository extends JpaRepository<UserRating, UUID> {
}
