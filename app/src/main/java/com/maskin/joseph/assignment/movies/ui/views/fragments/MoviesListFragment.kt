package com.maskin.joseph.assignment.movies.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maskin.joseph.assignment.movies.R
import com.maskin.joseph.assignment.movies.model.Movie
import com.maskin.joseph.assignment.movies.ui.adapters.MoviesAdapter
import com.maskin.joseph.assignment.movies.ui.listeners.OnMovieClickedListener
import com.maskin.joseph.assignment.movies.ui.utilities.Resource
import com.maskin.joseph.assignment.movies.ui.viewmodels.MoviesListViewModel
import kotlinx.android.synthetic.main.movies_list_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MoviesListFragment : Fragment(), OnMovieClickedListener {
    private val viewModel: MoviesListViewModel by viewModels()
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var viewAdapter: MoviesAdapter

    companion object {
        fun newInstance() = MoviesListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movies_list_fragment, container, false)

        // Initiate recyclerView
        setUpRecyclerView(view)

        return view
    }

    @ExperimentalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.moviesLiveData.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Resource.Status.LOADING -> {
                    loading_progress_bar.visibility = View.VISIBLE
                }

                Resource.Status.SUCCESS -> {
                    loading_progress_bar.visibility = View.GONE

                    // Update the list
                    resource.data?.let { movies ->
                        viewAdapter.updateDataSet(movies)
                    }
                }

                Resource.Status.ERROR -> {
                    loading_progress_bar.visibility = View.GONE
                    Toast.makeText(
                        context,
                        resource.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    private fun setUpRecyclerView(view: View) {
        moviesRecyclerView = view.findViewById(R.id.movies_rv)
        viewAdapter = MoviesAdapter(ArrayList(), this)
        moviesRecyclerView.adapter = viewAdapter
        moviesRecyclerView.layoutManager = LinearLayoutManager(context)
        moviesRecyclerView.setHasFixedSize(true)
    }

    override fun onMovieClicked(movie: Movie) {
        // Open details fragment and pass the clicked movie to it
        val action = MoviesListFragmentDirections
            .actionMoviesListFragmentToMovieDetailsFragment(movie)
        NavHostFragment.findNavController(this).navigate(action)
    }
}