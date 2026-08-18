package com.example.MyMovies.UserRating;

import com.example.MyMovies.dtos.AddRatingDto;
import com.example.MyMovies.movie.Movie;
import com.example.MyMovies.movie.MovieRepository;
import com.example.MyMovies.user.User;
import com.example.MyMovies.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class UserRatingService {

    UserRatingRepository jpa;
    MovieRepository movieJpa;
    UserRepository UserJpa;

    public UserRatingService(UserRatingRepository jpa, MovieRepository movieJpa, UserRepository userJpa) {
        this.jpa = jpa;
        this.movieJpa = movieJpa;
        UserJpa = userJpa;
    }

    public void addRevewe(String id, AddRatingDto addRatingDto) {
        creatIfNotExist(addRatingDto.tmdbId());
        Movie movie = movieJpa.findMovieByTmdbId(addRatingDto.tmdbId());
        User user = UserJpa.findById(id).orElseThrow(() -> new NoSuchElementException("user dons not exist"));
        UserRating userRating = new UserRating(null, movie, user, addRatingDto.rating(), addRatingDto.content(), LocalDate.now());
        jpa.save(userRating);
    }

    private void creatIfNotExist(Long tmdbId) {
        if (movieJpa.findByTmdbId(tmdbId)) {
            Movie movie = new Movie();
            movie.setTmdbId(tmdbId);
            movieJpa.save(movie);
        }
    }
}

