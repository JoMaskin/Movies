package com.maskin.joseph.assignment.movies.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.maskin.joseph.assignment.movies.R
import com.maskin.joseph.assignment.movies.model.Movie
import com.maskin.joseph.assignment.movies.ui.listeners.OnMovieClickedListener
import com.maskin.joseph.assignment.movies.ui.utilities.API_IMAGES_BASE_URL
import com.maskin.joseph.assignment.movies.ui.utilities.setImage

class MoviesAdapter(
    private var dataSet: ArrayList<Movie>,
    private val movieClickedListener: OnMovieClickedListener?
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_movie_viewholder, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(dataSet[holder.adapterPosition], movieClickedListener)
    }

    fun updateDataSet(newDataSet: List<Movie>) {
        dataSet = ArrayList()
        dataSet.addAll(newDataSet)
        notifyDataSetChanged()
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.movie_name)
        private val image: ImageView = itemView.findViewById(R.id.movie_image)

        fun bind(movie: Movie, movieClickedListener: OnMovieClickedListener?) {
            this.name.text = movie.title
            this.image.apply {
                val holder = ContextCompat.getDrawable(context, R.drawable.ic_row_movie_placeholder)
                setImage(
                    API_IMAGES_BASE_URL + movie.posterPath,
                    300,
                    300,
                    holder,
                    context
                )
            }

            // Fire click event to the subscribers
            movieClickedListener?.let { listener ->
                itemView.setOnClickListener {
                    listener.onMovieClicked(movie)
                }
            }
        }
    }
}