package com.example.MyMovies.movie;

import jakarta.persistence.*;
import org.springframework.core.SpringVersion;

import java.util.UUID;

@Entity
@Table(name = "movie")
public class movie {
    @Id
    @Column(name = "movie_id")
    private UUID id;

    private double avgRating;
}
