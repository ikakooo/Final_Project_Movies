package com.example.movieapplication.bottom_navigation.favourites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.home.adapters.PopularAdapter
import com.example.movieapplication.bottom_navigation.home.adapters.TopTodayAdapter
import com.example.movieapplication.bottom_navigation.home.models.Movies
import com.example.movieapplication.detailed_movie_view.DetailedMovieActivity
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import com.example.movieapplication.local_data_base.RoomFavouriteMovieModel
import kotlinx.android.synthetic.main.fragment_favourites.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class FavouritesFragment : Fragment() {
    private var favouritesMoviesList = mutableListOf<RoomFavouriteMovieModel>()
    private lateinit var favouritesAdapter: FavouritesAdapter
    private lateinit var favouritesViewModel: FavouritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouritesViewModel = ViewModelProvider(this).get(FavouritesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favourites, container, false)


        init(root)

        favouritesViewModel.favouritesMoviesLiveData.observe(viewLifecycleOwner, Observer {

            favouritesMoviesList.addAll(it)
            favouritesAdapter.notifyDataSetChanged()
        })

        return root
    }

    private fun init(root: View){
        favouritesAdapter =
            FavouritesAdapter(
                favouritesMoviesList,
                object : DetailedMovieListener {
                    override fun detailedViewClick(position: Int) {
                        val topTodayMovie = favouritesMoviesList[position]
                        val intent = Intent(context, DetailedMovieActivity::class.java)
                        intent.putExtra("name", topTodayMovie.path)
                        intent.putExtra("movieID", topTodayMovie.id)
                        startActivity(intent)
                    }
                })
        root.favouritesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        root.favouritesRecyclerView.isNestedScrollingEnabled = true
        root.favouritesRecyclerView.setHasFixedSize(false)
        root.favouritesRecyclerView.adapter = favouritesAdapter
    }
}