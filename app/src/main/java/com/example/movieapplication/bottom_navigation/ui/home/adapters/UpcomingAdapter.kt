package com.example.movieapplication.bottom_navigation.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.network_https.movie
import kotlinx.android.synthetic.main.items_layout.view.*


class UpcomingAdapter(val upComingMoviesList: MutableList<movie>) :
    RecyclerView.Adapter<UpcomingAdapter.ViewHolder>() {
    val imgBaseURL = "https://image.tmdb.org/t/p/w780/"

    override fun getItemCount(): Int {
        return upComingMoviesList.size
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

        private lateinit var model: movie

        fun onBind() {
            model = upComingMoviesList[adapterPosition]
            itemView.title.text = model.original_title
            Glide.with(itemView.context).load(imgBaseURL + model.poster_path).into(itemView.moviesImageViewID)

        }

    }

}