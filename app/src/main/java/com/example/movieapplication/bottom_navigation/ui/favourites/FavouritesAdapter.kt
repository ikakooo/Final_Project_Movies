package com.example.movieapplication.bottom_navigation.ui.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.ui.home.models.Movies
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import kotlinx.android.synthetic.main.favourites_layout.view.*


class FavouritesAdapter(private val favourites: MutableList<Movies>, val detailedMovieListener: DetailedMovieListener) :
    RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {
    val imgBaseURL = "https://image.tmdb.org/t/p/w780/"

    override fun getItemCount(): Int {
        return favourites.size
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

        private lateinit var model:Movies

        fun onBind() {
            model = favourites[adapterPosition]
            itemView.deleteFavourite.setImageResource(model.deleteFavourite)
            itemView.setOnClickListener {
                detailedMovieListener.detailedViewClick(adapterPosition)
            }
        }
    }
}