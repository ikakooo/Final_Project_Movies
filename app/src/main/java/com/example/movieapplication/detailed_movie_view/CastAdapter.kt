package com.example.movieapplication.detailed_movie_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.detailed_movie_view.model.MovieCastResponse
import kotlinx.android.synthetic.main.items_layout.view.*

class CastAdapter(val castList: MutableList<MovieCastResponse.MovieCast>, val detailedMovieListener: DetailedMovieListener) :
    RecyclerView.Adapter<CastAdapter.ViewHolder>() {
    val imgBaseURL = "https://image.tmdb.org/t/p/w780/"

    override fun getItemCount(): Int = castList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.items_layout, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var model: MovieCastResponse.MovieCast

        fun onBind() {
            model = castList[adapterPosition]
            itemView.title.text = model.name
            Glide.with(itemView.context).load(imgBaseURL + model.profile_path)
                .into(itemView.moviesImageViewID)
            itemView.setOnClickListener{
                detailedMovieListener.detailedViewClick(adapterPosition)
            }
        }

    }

}