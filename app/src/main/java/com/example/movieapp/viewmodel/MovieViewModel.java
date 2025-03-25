package com.example.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.data.model.Movie;
import com.example.movieapp.data.model.MovieSearchResponse;
import com.example.movieapp.data.repository.MovieRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    private final MovieRepository repository = new MovieRepository();

    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    // Search for movies from OMDB
    public void searchMovies(String query) {
        repository.searchMovies(query).enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null && "True".equals(response.body().getResponse())) {
                    movies.postValue(response.body().getSearchResults());
                } else {
                    errorMessage.postValue("No results found");
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                errorMessage.postValue("Failed to connect: " + t.getMessage());
            }
        });
    }
}
