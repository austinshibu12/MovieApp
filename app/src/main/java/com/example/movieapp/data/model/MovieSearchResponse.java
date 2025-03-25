package com.example.movieapp.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieSearchResponse {

    @SerializedName("Search")
    private List<Movie> searchResults;

    @SerializedName("totalResults")
    private String totalResults;

    @SerializedName("Response")
    private String response;

    @SerializedName("Error")
    private String error;

    public List<Movie> getSearchResults() {
        return searchResults;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return response;
    }

    public String getError() {
        return error;
    }
}
