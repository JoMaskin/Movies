package com.maskin.joseph.assignment.movies.data.repository

import com.maskin.joseph.assignment.movies.data.sources.local.LocalDataSource
import com.maskin.joseph.assignment.movies.data.sources.local.roomdb.entities.MovieEntity
import com.maskin.joseph.assignment.movies.data.sources.remote.RemoteDataSource
import com.maskin.joseph.assignment.movies.model.MoviesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object MoviesRepository {

    /**
     * This method get a list of (20) latest movies from the remote.
     * @param endDate maximal release date (format 1970-01-01).
     * @return Flow of list of movies.
     */
    @ExperimentalCoroutinesApi
    fun getMovies(endDate: String): Flow<MoviesList> {
        return flow {
            val movies = RemoteDataSource.apiClient.getMovies(endDate)
            emit(movies)
        }.flowOn(Dispatchers.IO)
    }

    /**
     * This method get the favorite movies from the local DB.
     * @return Flow of list of movie entities.
     */
    fun getFavoriteMoviesFromLocal(): Flow<List<MovieEntity>> {
        return LocalDataSource.roomDb.movieDao().getFavoriteMovies()
    }

    /**
     * This method check if a movie exist in the db.
     * @param id - Id of the movie to check existence.
     * @return true if exist, false if not.
     */
    suspend fun isMovieExist(id: Int): Boolean {
        return LocalDataSource.roomDb.movieDao().isMovieExists(id);
    }

    /**
     * This method inserts a single movie to the local DB.
     * @param movie - Movie to insert.
     */
    fun insertMovieToLocal(movie: MovieEntity) {
        LocalDataSource.roomDb.movieDao().insert(movie)
    }

    /**
     * This method remove a single movie from the local DB.
     * @param id - Id of the Movie to delete.
     */
    fun deleteMovieFromLocal(id: Int) {
        LocalDataSource.roomDb.movieDao().delete(id)
    }
}