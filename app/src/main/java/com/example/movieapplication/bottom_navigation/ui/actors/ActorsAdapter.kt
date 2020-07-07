package com.example.movieapplication.bottom_navigation.ui.actors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import com.example.movieapplication.network_https.models.movie
import kotlinx.android.synthetic.main.items_layout.view.*

class ActorsAdapter(private val actorsList: MutableList<ActorsResponse.Actor>, val detailedMovieListener: DetailedMovieListener) :
    RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {
    val imgBaseURL = "https://image.tmdb.org/t/p/w780/"

    override fun getItemCount(): Int {
        return actorsList.size
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

        private lateinit var model: ActorsResponse.Actor

        fun onBind() {
            model = actorsList[adapterPosition]
            itemView.title.text = model.name
            Glide.with(itemView.context).load(imgBaseURL + model.profile_path).into(itemView.moviesImageViewID)
            itemView.setOnClickListener {
                detailedMovieListener.detailedViewClick(adapterPosition)
            }

        }

    }

}