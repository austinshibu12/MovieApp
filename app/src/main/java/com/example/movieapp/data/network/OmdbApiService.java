package com.example.movieapp.data.network;

import com.example.movieapp.data.model.Movie;
import com.example.movieapp.data.model.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApiService {

    @GET("/")
    Call<MovieSearchResponse> searchMovies(
            @Query("apikey") String apiKey,
            @Query("s") String query
    );

    @GET("/")
    Call<Movie> getMovieDetails(
            @Query("apikey") String apiKey,
            @Query("i") String imdbID
    );
}
