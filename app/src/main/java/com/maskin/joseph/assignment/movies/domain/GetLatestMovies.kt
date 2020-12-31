package com.maskin.joseph.assignment.movies.domain

import com.maskin.joseph.assignment.movies.data.repository.MoviesRepository
import com.maskin.joseph.assignment.movies.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*

class GetLatestMovies {
    private var endDate: String

    init {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        endDate = simpleDateFormat.format(Date())
    }

    @ExperimentalCoroutinesApi
    fun execute(): Flow<List<Movie>> {
        return MoviesRepository.getMovies(endDate).map { it.movies }
    }
}