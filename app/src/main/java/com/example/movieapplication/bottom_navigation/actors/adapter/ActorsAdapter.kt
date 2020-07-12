package com.example.movieapplication.bottom_navigation.actors.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.actors.model.ActorsResponseModel
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import kotlinx.android.synthetic.main.items_layout.view.*

class ActorsAdapter(private val actorsListModel: MutableList<ActorsResponseModel.Actor>, val detailedMovieListener: DetailedMovieListener) :
    RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {
    val imgBaseURL = "https://image.tmdb.org/t/p/w780/"

    override fun getItemCount(): Int {
        return actorsListModel.size
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

        private lateinit var model: ActorsResponseModel.Actor

        fun onBind() {
            model = actorsListModel[adapterPosition]
            itemView.title.text = model.name
            itemView.PopularityActorTitleID.isVisible = true
            itemView.PopularityActorTitleID.text = model.popularity.toString()
            Glide.with(itemView.context).load(imgBaseURL + model.profile_path).into(itemView.moviesImageViewID)
            itemView.setOnClickListener {
                detailedMovieListener.detailedViewClick(adapterPosition)
            }
        }
    }
}