package com.maskin.joseph.assignment.movies.ui.views.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maskin.joseph.assignment.movies.R
import com.maskin.joseph.assignment.movies.ui.adapters.MoviesAdapter
import com.maskin.joseph.assignment.movies.ui.utilities.Resource
import com.maskin.joseph.assignment.movies.ui.viewmodels.FavoriteListViewModel
import kotlinx.android.synthetic.main.activity_favorite_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

class FavoriteListActivity : AppCompatActivity() {
    private val viewModel: FavoriteListViewModel by viewModels()
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var viewAdapter: MoviesAdapter

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_list)

        // Initiate recyclerView
        setUpRecyclerView()

        viewModel.favoriteList.observe(this, { resource ->
            when (resource.status) {
                Resource.Status.LOADING -> {
                    favorite_loading_progress_bar.visibility = View.VISIBLE
                }

                Resource.Status.SUCCESS -> {
                    favorite_loading_progress_bar.visibility = View.GONE

                    // Update the list
                    resource.data?.let { movies ->
                        viewAdapter.updateDataSet(movies)
                    }
                }

                Resource.Status.ERROR -> {
                    favorite_loading_progress_bar.visibility = View.GONE
                    Toast.makeText(
                        this,
                        resource.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    private fun setUpRecyclerView() {
        moviesRecyclerView = findViewById(R.id.favorite_rv)
        viewAdapter = MoviesAdapter(ArrayList(), null)
        moviesRecyclerView.adapter = viewAdapter
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        moviesRecyclerView.setHasFixedSize(true)
    }
}