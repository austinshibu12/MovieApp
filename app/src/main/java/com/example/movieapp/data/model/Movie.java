package com.example.movieapp.data.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("imdbID")
    private String imdbID;

    @SerializedName("Type")
    private String type;

    @SerializedName("Poster")
    private String poster;

    // Getters (needed for RecyclerView & ViewModel)
    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return type;
    }

    public String getPoster() {
        return poster;
    }

    @SerializedName("Rated")
    private String rated;

    @SerializedName("Production")
    private String production;

    public String getRated() {
        return rated;
    }

    public String getProduction() {
        return production;
    }

}
