package com.maskin.joseph.assignment.movies.model

import com.google.gson.annotations.SerializedName

data class MoviesList(
    @SerializedName("results")
    val movies: List<Movie>
)