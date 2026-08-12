package com.example.MyMovies.tmdb;

import com.example.MyMovies.tmdb.tmdbDtos.TmdbMovieDto;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbResponseDto;
import com.example.MyMovies.tmdb.tmdbDtos.TmdbResultsReviewsDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class TmdbClient {

    private final RestClient restClient;

    public TmdbClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public TmdbResponseDto trendingMovies() {
        return restClient.get()
                .uri("/trending/movie/week")
                .retrieve()
                .body(TmdbResponseDto.class);
    }

    public TmdbResponseDto upcomingMovies(){
        return restClient.get()
                .uri("/movie/upcoming")
                .retrieve()
                .body(TmdbResponseDto.class);
    }

    public TmdbResponseDto nowPlayingMovies(){
        return restClient.get()
                .uri("/movie/now_playing")
                .retrieve()
                .body(TmdbResponseDto.class);
    }

    public TmdbResponseDto topRatedMovies(){
        return restClient.get()
                .uri("/movie/top_rated")
                .retrieve()
                .body(TmdbResponseDto.class);
    }

    public TmdbResponseDto popularMovies(){
        return restClient.get()
                .uri("/movie/popular")
                .retrieve()
                .body(TmdbResponseDto.class);
    }

    public TmdbMovieDto tmdbMovieDetails(long id){
        return restClient.get()
                .uri("/movie/" + id)
                .retrieve()
                .body(TmdbMovieDto.class);
    }


    public TmdbResponseDto similarMoviesById(long id) {
        return restClient.get()
                .uri("/movie/" + id + "/similar")
                .retrieve()
                .body(TmdbResponseDto.class);
    }

    public TmdbResultsReviewsDto reviewsForMovie(long id) {
        return restClient.get()
                .uri("/movie/" + id + "/reviews")
                .retrieve()
                .body(TmdbResultsReviewsDto.class);
    }

}