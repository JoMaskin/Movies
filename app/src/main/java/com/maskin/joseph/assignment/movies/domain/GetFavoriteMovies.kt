package com.maskin.joseph.assignment.movies.domain

import com.maskin.joseph.assignment.movies.data.repository.MoviesRepository
import com.maskin.joseph.assignment.movies.data.sources.local.roomdb.entities.MovieEntity
import com.maskin.joseph.assignment.movies.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoriteMovies {
    fun execute(): Flow<List<Movie>> {
        return MoviesRepository.getFavoriteMoviesFromLocal().map { list ->
            list.map { movie ->
                convertMovieEntityToMovie(movie)
            }
        }
    }

    private fun convertMovieEntityToMovie(movie: MovieEntity): Movie {
        return Movie(
            movie.id,
            movie.title,
            movie.posterPath,
            movie.description,
            movie.rating,
            movie.releaseYear
        )
    }
}