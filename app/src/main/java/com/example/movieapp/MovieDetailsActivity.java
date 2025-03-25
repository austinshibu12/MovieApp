package com.example.movieapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.movieapp.data.model.Movie;
import com.example.movieapp.data.repository.MovieRepository;
import com.example.movieapp.databinding.ActivityMovieDetailsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailsBinding binding;
    private final MovieRepository repository = new MovieRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get imdbID passed from MainActivity
        String imdbID = getIntent().getStringExtra("imdbID");

        if (imdbID != null) {
            fetchMovieDetails(imdbID);
        } else {
            Toast.makeText(this, "No movie ID provided", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void fetchMovieDetails(String imdbID) {
        repository.getMovieDetails(imdbID).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Movie movie = response.body();
                    binding.movieTitle.setText(movie.getTitle());
                    binding.movieYear.setText("Year: " + movie.getYear());
                    binding.movieType.setText("Type: " + movie.getType());
                    binding.movieRated.setText("Rated: " + movie.getRated());
                    binding.movieStudio.setText("Studio: " + movie.getProduction());

                    Glide.with(MovieDetailsActivity.this)
                            .load(movie.getPoster())
                            .into(binding.moviePoster);
                } else {
                    Toast.makeText(MovieDetailsActivity.this, "Movie not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(MovieDetailsActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
