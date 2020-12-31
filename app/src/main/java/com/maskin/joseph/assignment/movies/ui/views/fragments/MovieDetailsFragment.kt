package com.maskin.joseph.assignment.movies.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.maskin.joseph.assignment.movies.R
import com.maskin.joseph.assignment.movies.ui.utilities.API_IMAGES_BASE_URL
import com.maskin.joseph.assignment.movies.ui.utilities.setImage
import com.maskin.joseph.assignment.movies.ui.viewmodels.MovieDetailsViewModel
import java.util.*

class MovieDetailsFragment : Fragment() {
    private val movieArg: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModels()

    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var rating: TextView
    private lateinit var releaseYear: TextView
    private lateinit var description: TextView
    private lateinit var isFavoriteCheckBox: CheckBox

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_details_fragment, container, false)

        // Initiate views
        initViews(view)

        return view
    }

    private fun initViews(view: View) {
        image = view.findViewById(R.id.movie_details_image)
        title = view.findViewById(R.id.movie_details_title)
        rating = view.findViewById(R.id.movie_details_rating)
        releaseYear = view.findViewById(R.id.movie_details_release_year)
        description = view.findViewById(R.id.movie_overview)
        isFavoriteCheckBox = view.findViewById(R.id.favorite_checkbox)

        title.text = movieArg.movie.title

        val placeholder =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_movie_placeholder)
        image.setImage(
            API_IMAGES_BASE_URL + movieArg.movie.posterPath,
            800,
            1000,
            placeholder,
            requireContext()
        )

        rating.text =
            String.format(
                "${getString(R.string.rating)} " +
                        "${movieArg.movie.rating} ${getString(R.string.star_character)}"
            )

        val calendar = GregorianCalendar()
        calendar.time = movieArg.movie.releaseDate
        releaseYear.text =
            String.format("${getString(R.string.release_year)} ${calendar.get(Calendar.YEAR)}")

        description.text =
            String.format("${getString(R.string.overview)} ${movieArg.movie.description}")

        isFavoriteCheckBox.isChecked = viewModel.isFavorite(movieArg.movie)

        isFavoriteCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.addToFavorites(movieArg.movie)
            } else {
                viewModel.removeFromFavorites(movieArg.movie)
            }
        }
    }
}