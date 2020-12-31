package com.maskin.joseph.assignment.movies.domain

import com.maskin.joseph.assignment.movies.data.repository.MoviesRepository
import com.maskin.joseph.assignment.movies.data.sources.local.roomdb.entities.MovieEntity
import com.maskin.joseph.assignment.movies.model.Movie
import java.util.*

class AddToFavorites {
    fun execute(movie: Movie) {
        MoviesRepository.insertMovieToLocal(convertMovieToMovieEntity(movie))
    }

    private fun convertMovieToMovieEntity(movie: Movie) : MovieEntity {
        val calendar = GregorianCalendar()
        calendar.time = movie.releaseDate
        return MovieEntity(movie.id,
            movie.title,
            movie.description,
            movie.posterPath,
            movie.rating,
            movie.releaseDate)
    }
}