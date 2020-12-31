package com.maskin.joseph.assignment.movies.data.sources.remote

import com.maskin.joseph.assignment.movies.data.sources.remote.retrofit.API_KEY
import com.maskin.joseph.assignment.movies.data.sources.remote.retrofit.API_KEY_PARAM_NAME
import com.maskin.joseph.assignment.movies.data.sources.remote.retrofit.API_MOVIES_URL
import com.maskin.joseph.assignment.movies.data.sources.remote.retrofit.MoviesApiClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource {
    private val httpClient = OkHttpClient.Builder()

    init {
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val httpUrl = originalHttpUrl.newBuilder()
                .addQueryParameter(API_KEY_PARAM_NAME, API_KEY)
                .build()

            val requestBuilder = original.newBuilder()
                .url(httpUrl)

            val request = requestBuilder.build()

            chain.proceed(request)
        }
    }

    val apiClient: MoviesApiClient = Retrofit.Builder()
        .baseUrl(API_MOVIES_URL)
        .client(httpClient.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MoviesApiClient::class.java)
}