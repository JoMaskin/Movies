package com.maskin.joseph.assignment.movies.data.sources.local.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maskin.joseph.assignment.movies.data.sources.local.roomdb.entities.MovieEntity
import com.maskin.joseph.assignment.movies.data.sources.local.roomdb.dao.MovieDao

@Database(entities = [MovieEntity::class], version = 1)
abstract class RoomDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}