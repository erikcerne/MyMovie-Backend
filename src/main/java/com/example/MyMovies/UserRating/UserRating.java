package com.example.MyMovies.UserRating;

import com.example.MyMovies.movie.Movie;
import com.example.MyMovies.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "user_rating")
@NoArgsConstructor
@AllArgsConstructor
    public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "rating_id")
    private UUID ratingId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private User user;

    private int rating;
    private String comment;
}