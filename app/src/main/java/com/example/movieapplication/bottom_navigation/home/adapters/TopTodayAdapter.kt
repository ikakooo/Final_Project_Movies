package com.example.movieapplication.bottom_navigation.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import com.example.movieapplication.bottom_navigation.home.models.Movies
import kotlinx.android.synthetic.main.items_layout.view.*

class TopTodayAdapter(val topTodayMoviesList: MutableList<Movies>, val detailedMovieListener: DetailedMovieListener) :
    RecyclerView.Adapter<TopTodayAdapter.ViewHolder>() {
    val imgBaseURL = "https://image.tmdb.org/t/p/w780/"

    override fun getItemCount(): Int {
        return topTodayMoviesList.size
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
            model = topTodayMoviesList[adapterPosition]
            itemView.title.text = model.original_title
            Glide.with(itemView.context).load(imgBaseURL + model.poster_path).into(itemView.moviesImageViewID)
            itemView.setOnClickListener {
                detailedMovieListener.detailedViewClick(adapterPosition)
            }
        }

    }

}