package com.example.MyMovies.movieUser;

import com.example.MyMovies.movie.Movie;
import com.example.MyMovies.user.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "user_rating")
public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_rating_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
