package com.example.MyMovies.UserMovie;

import com.example.MyMovies.dtos.AddRatingDto;
import com.example.MyMovies.dtos.GetAllUserMoviesDto;
import com.example.MyMovies.movie.Movie;
import com.example.MyMovies.movie.MovieRepository;
import com.example.MyMovies.tmdb.TmdbClient;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbMovieDto;
import com.example.MyMovies.user.User;
import com.example.MyMovies.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserMovieService {

    UserMovieRepository jpa;
    MovieRepository movieJpa;
    UserRepository UserJpa;
    TmdbClient tmdbClient;

    public UserMovieService(UserMovieRepository jpa, MovieRepository movieJpa, TmdbClient tmdbClient, UserRepository userJpa) {
        this.jpa = jpa;
        this.movieJpa = movieJpa;
        this.tmdbClient = tmdbClient;
        UserJpa = userJpa;
    }

    public void addRevewe(String authId, AddRatingDto addRatingDto) {
        if (movieJpa.findByTmdbId(addRatingDto.tmdbId())) {
            saveMovie(addRatingDto.tmdbId());
        }
        Movie movie = movieJpa.findMovieByTmdbId(addRatingDto.tmdbId());
        User user = UserJpa.findById(authId).orElseThrow(() -> new NoSuchElementException("user dons not exist"));
        UserMovie userMovie = new UserMovie(null, movie, user, WatchStatus.WATCHED, addRatingDto.rating(), addRatingDto.content(), LocalDate.now());
        jpa.save(userMovie);
    }

    public void addWatched(String authid, long tmdbId) {
        if (movieJpa.findByTmdbId(tmdbId)) {
            saveMovie(tmdbId);
        }
        Movie movie = movieJpa.findMovieByTmdbId(tmdbId);
        User user = UserJpa.findById(authid).orElseThrow(() -> new NoSuchElementException("user dons not exist"));
        UserMovie userMovie = new UserMovie(null, movie, user, WatchStatus.WATCHED, null, null, LocalDate.now());
        jpa.save(userMovie);
    }

    private void saveMovie(Long tmdbId) {
        TmdbMovieDto tmdbMovieDto = tmdbClient.tmdbMovieDetails(tmdbId);
        Movie movie = new Movie(null, tmdbId, new ArrayList<>(),
                tmdbMovieDto.originalTitle(),
                tmdbMovieDto.posterPath(),
                tmdbMovieDto.voteAverage(),
                tmdbMovieDto.popularity());
        movieJpa.save(movie);
    }

    public List<GetAllUserMoviesDto> getAllRatings(String authId) {
        List<UserMovie> userMovies = jpa.findAllByUser_UserId(authId);
        return userMovies.stream().map(i ->
                new GetAllUserMoviesDto(i.getComment(),
                        i.getRating(),
                        i.getMovie().getTmdbId(),
                        i.getStatus(),
                        i.getDate(),
                        i.getMovie().getOriginalTitle(),
                        i.getMovie().getOriginalTitle(),
                        i.getMovie().getPopularity())).toList();
    }


}
