package com.maskin.joseph.assignment.movies.data.sources.local

import androidx.room.Room
import com.maskin.joseph.assignment.movies.app.MoviesApplication.Companion.context
import com.maskin.joseph.assignment.movies.data.sources.local.roomdb.RoomDb

object LocalDataSource {
    val roomDb = Room.databaseBuilder(
        context,
        RoomDb::class.java,
        "favorite_movies_database"
    ).build()
}