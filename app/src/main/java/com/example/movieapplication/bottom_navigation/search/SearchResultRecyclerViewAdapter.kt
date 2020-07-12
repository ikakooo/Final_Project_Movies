package com.example.movieapplication.bottom_navigation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.search.models.SearchResultModelResultList
import com.example.movieapplication.constants.Constants.BASE_IMG_URL
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import kotlinx.android.synthetic.main.items_layout.view.*


class SearchResultRecyclerViewAdapter(val searchResultByNameMoviesList: MutableList<SearchResultModelResultList>, val detailedMovieListener: DetailedMovieListener) :
    RecyclerView.Adapter<SearchResultRecyclerViewAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return searchResultByNameMoviesList.size
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

        private lateinit var model: SearchResultModelResultList

        fun onBind() {
            model = searchResultByNameMoviesList[adapterPosition]
            itemView.title.text = model.original_title
            Glide.with(itemView.context).load(BASE_IMG_URL + model.poster_path).into(itemView.moviesImageViewID)
            itemView.setOnClickListener{
                detailedMovieListener.detailedViewClick(adapterPosition)
            }
        }

    }

}