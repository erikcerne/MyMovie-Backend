package com.example.MyMovies.UserMovie;

import com.example.MyMovies.movie.Movie;
import com.example.MyMovies.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "user_movie")
@NoArgsConstructor
@AllArgsConstructor
public class UserMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_movie_id")
    private UUID movieUserId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private WatchStatus status;

    private Integer rating;

    private String comment;

    private LocalDate addedDate;
}