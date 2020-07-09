package com.example.movieapplication.bottom_navigation.ui.home.more_movies_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.ui.home.models.Movies
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import kotlinx.android.synthetic.main.items_layout.view.*


class MoreMoviesRecyclerviewAdapter(private val allMoviesList: MutableList<Movies>, val detailedMovieListener: DetailedMovieListener) :
    RecyclerView.Adapter<MoreMoviesRecyclerviewAdapter.ViewHolder>() {
    val imgBaseURL = "https://image.tmdb.org/t/p/w780/"

    override fun getItemCount(): Int {
        return allMoviesList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_layout, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.onBind()

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var model: Movies

        fun onBind() {
            model = allMoviesList[adapterPosition]
            itemView.title.text = model.original_title
            Glide.with(itemView.context).load(imgBaseURL + model.poster_path).into(itemView.moviesImageViewID)
            itemView.setOnClickListener {
                detailedMovieListener.detailedViewClick(adapterPosition)
            }
        }
    }
}