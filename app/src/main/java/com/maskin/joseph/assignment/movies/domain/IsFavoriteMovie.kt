package com.maskin.joseph.assignment.movies.domain

import com.maskin.joseph.assignment.movies.data.repository.MoviesRepository
import com.maskin.joseph.assignment.movies.model.Movie

class IsFavoriteMovie {
    suspend fun execute(movie: Movie): Boolean {
        return MoviesRepository.isMovieExist(movie.id)
    }
}