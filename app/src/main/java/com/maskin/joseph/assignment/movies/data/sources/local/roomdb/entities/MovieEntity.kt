package com.maskin.joseph.assignment.movies.data.sources.local.roomdb.entities

import androidx.room.*
import com.maskin.joseph.assignment.movies.data.sources.local.roomdb.utilities.DateTypeConverter
import java.util.*

@Entity(tableName = "movies")
@TypeConverters(DateTypeConverter::class)
data class MovieEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Overview") val description: String,
    @ColumnInfo(name = "PosterPath") val posterPath: String,
    @ColumnInfo(name = "Rating") val rating: String,
    @ColumnInfo(name = "ReleaseYear") val releaseYear: Date,
)