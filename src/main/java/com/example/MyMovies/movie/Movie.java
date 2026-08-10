package com.example.MyMovies.movie;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @Column(name = "movie_id")
    private UUID id;

    private double avgRating;
}
