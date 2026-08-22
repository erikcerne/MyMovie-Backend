package com.example.MyMovies.UserMovie;

import com.example.MyMovies.dtos.AddRatingDto;
import com.example.MyMovies.dtos.AllUserMoviesDto;
import com.example.MyMovies.dtos.UserMoviesDto;
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
        if (!movieJpa.existsByTmdbId(addRatingDto.tmdbId())) {
            saveMovie(addRatingDto.tmdbId());
        }
        if (jpa.existsUserMovieByUser_UserIdAndMovie_TmdbId(authId, addRatingDto.tmdbId())) {
            throw new RuntimeException("Movie already exist in user movies");
        }
        Movie movie = movieJpa.findMovieByTmdbId(addRatingDto.tmdbId());
        User user = UserJpa.findById(authId).orElseThrow(() -> new NoSuchElementException("User does not exist"));
        UserMovie userMovie = new UserMovie(null,
                movie, user,
                WatchStatus.WATCHED,
                addRatingDto.rating(),
                addRatingDto.content(),
                LocalDate.now());
        jpa.save(userMovie);
    }

    public void addWatch(String authId, long tmdbId, WatchStatus status) {
        if (!movieJpa.existsByTmdbId(tmdbId)) {
            saveMovie(tmdbId);
        }
        if (jpa.existsUserMovieByUser_UserIdAndMovie_TmdbId(authId, tmdbId)) {
            throw new RuntimeException("Movie already exist in user movies");
        }
        Movie movie = movieJpa.findMovieByTmdbId(tmdbId);
        User user = UserJpa.findById(authId).orElseThrow(() -> new NoSuchElementException("User does not exist"));
        UserMovie userMovie = new UserMovie(null, movie, user, status, null, null, LocalDate.now());
        jpa.save(userMovie);
    }

    private void saveMovie(Long tmdbId) {
        TmdbMovieDto tmdbMovieDto = tmdbClient.tmdbMovieDetails(tmdbId);
        Movie movie = new Movie(null,
                tmdbId,
                new ArrayList<>(),
                tmdbMovieDto.originalTitle(),
                tmdbMovieDto.posterPath(),
                tmdbMovieDto.voteAverage(),
                tmdbMovieDto.releaseDate());
        movieJpa.save(movie);
    }

    public AllUserMoviesDto getAllRatings(String authId) {

        List<UserMovie> userMovies = jpa.findAllByUser_UserId(authId);
        List<UserMoviesDto> allWatched = filterOnStatus(WatchStatus.WATCHED, userMovies);
        List<UserMoviesDto> wantToWatch = filterOnStatus(WatchStatus.WANT_TO_WATCH, userMovies);

        List<UserMoviesDto> reviewed = allWatched.stream().filter(i -> i.rating() != null).toList();
        List<UserMoviesDto> watched = allWatched.stream().filter(i -> i.rating() == null).toList();

        return new AllUserMoviesDto(reviewed, watched, wantToWatch);
    }

    private List<UserMoviesDto> filterOnStatus(WatchStatus status, List<UserMovie> userMovies) {
        return userMovies.stream().filter(i -> i.getStatus().equals(status))
                .map(i -> new UserMoviesDto(
                        i.getComment(),
                        i.getRating(),
                        i.getMovie().getTmdbId(),
                        i.getStatus(),
                        i.getAddedDate(),
                        i.getMovie().getOriginalTitle(),
                        i.getMovie().getPosterPath(),
                        i.getMovie().getVoteAverage(),
                        i.getMovie().getReleaseDate()))
                .toList();
    }

    public void deleteMovieFromLibrary(String authId, long tmdbId) {
        jpa.deleteByMovie_TmdbIdAndUser_UserId(tmdbId, authId);
    }

    public void updateStatus(String authId, long tmdbId, WatchStatus status) {
        UserMovie userMovie = jpa.findByUser_UserIdAndMovie_TmdbId(authId, tmdbId);
        userMovie.setStatus(status);
        userMovie.setAddedDate(LocalDate.now());
        userMovie.setComment(null);
        userMovie.setRating(null);
        jpa.save(userMovie);
    }

    public void updateReview(String authId, AddRatingDto addRatingDto) {
        UserMovie userMovie = jpa.findByUser_UserIdAndMovie_TmdbId(authId, addRatingDto.tmdbId());

        userMovie.setStatus(WatchStatus.WATCHED);
        userMovie.setRating(addRatingDto.rating());
        userMovie.setComment(addRatingDto.content());
        userMovie.setAddedDate(LocalDate.now());
        jpa.save(userMovie);
    }

}
