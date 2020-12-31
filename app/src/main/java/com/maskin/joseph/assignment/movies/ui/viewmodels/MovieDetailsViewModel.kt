package com.maskin.joseph.assignment.movies.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maskin.joseph.assignment.movies.domain.RemoveFromFavorites
import com.maskin.joseph.assignment.movies.domain.AddToFavorites
import com.maskin.joseph.assignment.movies.domain.IsFavoriteMovie
import com.maskin.joseph.assignment.movies.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieDetailsViewModel : ViewModel() {
    fun addToFavorites(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            AddToFavorites().execute(movie)
        }.invokeOnCompletion {
            Log.i(TAG, "Movie inserted successfully")
        }
    }

    fun removeFromFavorites(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            RemoveFromFavorites().execute(movie)
        }.invokeOnCompletion {
            Log.i(TAG, "Movie has been deleted successfully")
        }
    }

    fun isFavorite(movie: Movie): Boolean {
        var isFavorite = false
        runBlocking(Dispatchers.IO) {
            isFavorite = IsFavoriteMovie().execute(movie)
        }
        return isFavorite
    }

    companion object {
        const val TAG = "MovieDetailsViewModel "
    }
}