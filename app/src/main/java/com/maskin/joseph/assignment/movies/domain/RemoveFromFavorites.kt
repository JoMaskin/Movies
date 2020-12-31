package com.maskin.joseph.assignment.movies.domain

import com.maskin.joseph.assignment.movies.data.repository.MoviesRepository
import com.maskin.joseph.assignment.movies.model.Movie

class RemoveFromFavorites {
    fun execute(movie: Movie) {
        MoviesRepository.deleteMovieFromLocal(movie.id)
    }
}