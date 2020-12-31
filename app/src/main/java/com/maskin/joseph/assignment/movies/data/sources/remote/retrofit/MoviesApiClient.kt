package com.maskin.joseph.assignment.movies.data.sources.remote.retrofit

import com.maskin.joseph.assignment.movies.model.MoviesList
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiClient {

    @GET("$API_MOVIES_URL$API_DISCOVER_MOVIE_URL")
    suspend fun getMovies(@Query(API_CONDITION_END_RELEASE_DATE) endDate: String): MoviesList
}