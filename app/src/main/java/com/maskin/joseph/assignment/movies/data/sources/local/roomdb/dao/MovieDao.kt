package com.maskin.joseph.assignment.movies.data.sources.local.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.maskin.joseph.assignment.movies.data.sources.local.roomdb.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM movies WHERE id = :id)")
    suspend fun isMovieExists(id: Int): Boolean

    @Insert
    fun insert(movie: MovieEntity)

    @Query("DELETE from movies WHERE id = :id")
    fun delete(id: Int)
}