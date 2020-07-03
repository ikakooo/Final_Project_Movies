package com.example.movieapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.network_https.movie
import com.example.movieapplication.splash_screen.ModelItem
import kotlinx.android.synthetic.main.items_layout.view.*

class TopTodayAdapter(val ItemsList: MutableList<movie>) :
    RecyclerView.Adapter<TopTodayAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return ItemsList.size
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
            model = ItemsList[adapterPosition]
            itemView.title.text = model.original_title


        }

    }

}