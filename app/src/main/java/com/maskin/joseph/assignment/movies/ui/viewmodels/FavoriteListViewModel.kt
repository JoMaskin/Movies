package com.maskin.joseph.assignment.movies.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.maskin.joseph.assignment.movies.R
import com.maskin.joseph.assignment.movies.app.MoviesApplication
import com.maskin.joseph.assignment.movies.domain.GetFavoriteMovies
import com.maskin.joseph.assignment.movies.model.Movie
import com.maskin.joseph.assignment.movies.ui.utilities.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

class FavoriteListViewModel : ViewModel() {
    @ExperimentalCoroutinesApi
    val favoriteList: LiveData<Resource<List<Movie>>> get() = liveData {
        GetFavoriteMovies().execute()
            .onStart {
                emit(Resource.loading(null))
            }
            .catch { e ->
                Log.e(
                    TAG,
                    "Exception occurred while trying to get favorites movies",
                    e
                )
                emit(
                    Resource.error(
                        MoviesApplication.context.getString(R.string.error_loading_data),
                        null
                    )
                )
            }
            .collect {
                emit(Resource.success(it))
            }
    }

    companion object {
        const val TAG = "FavoriteListViewModel "
    }
}