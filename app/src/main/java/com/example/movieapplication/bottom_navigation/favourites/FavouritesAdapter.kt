package com.example.movieapplication.bottom_navigation.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.home.models.Movies
import com.example.movieapplication.constants.Constants.BASE_IMG_URL
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import com.example.movieapplication.local_data_base.RoomFavouriteMovieModel
import kotlinx.android.synthetic.main.favourites_layout.view.*
import kotlinx.android.synthetic.main.favourites_layout.view.moviesImageViewID
import kotlinx.android.synthetic.main.favourites_layout.view.title
import kotlinx.android.synthetic.main.items_layout.view.*


class FavouritesAdapter(private val favourites: MutableList<RoomFavouriteMovieModel>, val detailedMovieListener: DetailedMovieListener) :
    RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {

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

        private lateinit var model:RoomFavouriteMovieModel

        fun onBind() {
            model = favourites[adapterPosition]
            itemView.title.text = "model.original_title"
            Glide.with(itemView.context).load(BASE_IMG_URL + model.path).into(itemView.moviesImageViewID)
            itemView.setOnClickListener {
                detailedMovieListener.detailedViewClick(adapterPosition)
            }
        }
    }
}