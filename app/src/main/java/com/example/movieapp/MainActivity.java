package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.movieapp.data.model.Movie;
import com.example.movieapp.databinding.ActivityMainBinding;
import com.example.movieapp.ui.adapter.MovieAdapter;
import com.example.movieapp.viewmodel.MovieViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {

    private ActivityMainBinding binding;
    private MovieAdapter adapter;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up RecyclerView
        adapter = new MovieAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        // ViewModel setup
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        // Observe movie list
        viewModel.getMovies().observe(this, movies -> adapter.setMovieList(movies));

        // Observe error messages
        viewModel.getErrorMessage().observe(this, message -> {
            if (message != null) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        // Search button logic
        binding.searchButton.setOnClickListener(v -> {
            String query = binding.searchInput.getText().toString().trim();
            if (!query.isEmpty()) {
                viewModel.searchMovies(query);
            } else {
                Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Handle movie click
    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("imdbID", movie.getImdbID());
        startActivity(intent);
    }
}
