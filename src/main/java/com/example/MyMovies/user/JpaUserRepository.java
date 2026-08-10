package com.example.MyMovies.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserRepository, UUID> {
}
