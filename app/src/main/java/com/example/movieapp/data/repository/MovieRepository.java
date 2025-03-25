package com.example.movieapp.data.repository;

import com.example.movieapp.data.model.Movie;
import com.example.movieapp.data.model.MovieSearchResponse;
import com.example.movieapp.data.network.OmdbApiService;
import com.example.movieapp.data.network.RetrofitClient;

import retrofit2.Call;

public class MovieRepository {

    private static final String API_KEY = "b3be1a";
    private final OmdbApiService apiService;

    public MovieRepository() {
        apiService = RetrofitClient.getInstance();
    }


    public Call<MovieSearchResponse> searchMovies(String query) {
        return apiService.searchMovies(API_KEY, query);
    }

    public Call<Movie> getMovieDetails(String imdbID) {
        return apiService.getMovieDetails(API_KEY, imdbID);
    }
}
