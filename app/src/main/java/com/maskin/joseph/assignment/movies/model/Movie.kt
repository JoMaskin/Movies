package com.maskin.joseph.assignment.movies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("overview")
    val description: String,
    @SerializedName("vote_average")
    val rating: String,
    @SerializedName("release_date")
    val releaseDate: Date
) : Parcelable