package com.example.MyMovies.movie;

import com.example.MyMovies.UserMovie.UserMovie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "movie_id")
    private UUID movieId;

    @Column(name = "tmdb_id", unique = true)
    private long tmdbId;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<UserMovie> ratings;
    private String originalTitle;
    private String posterPath;
    private Double voteAverage;
    private Double popularity;
    private String releaseDate;
}