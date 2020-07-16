package com.example.movieapplication.bottom_navigation.favourites

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.detailed_movie_view.DetailedMovieActivity
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import com.example.movieapplication.local_data_base.DatabaseBuilder
import com.example.movieapplication.local_data_base.RoomFavouriteMovieModel
import kotlinx.android.synthetic.main.fragment_favourites.*

class FavouritesFragment : Fragment() {
    private var favouritesMoviesList = mutableListOf<RoomFavouriteMovieModel>()
    private lateinit var favouritesAdapter: FavouritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }
    override fun onResume() {
        super.onResume()
            init()
        favouritesMoviesList.clear()
        val dB = DatabaseBuilder.roomDB.favoriteDaoConnection().getFavouriteMovies().toMutableList()
        (0 until dB.size).forEach {
            favouritesMoviesList.add(RoomFavouriteMovieModel(dB[it].id?.toLong(),dB[it].movie_id.toString(),dB[it].path.toString(),dB[it].title.toString()))
        }
        favouritesAdapter.notifyDataSetChanged()
        d("fwfewfewf","fwewfew")
    }

    private fun init(){

        favouritesAdapter = FavouritesAdapter(favouritesMoviesList,
                object : DetailedMovieListener {
                    override fun detailedViewClick(position: Int) {
                        val favouritesMovie = favouritesMoviesList[position]
                        val intent = Intent(context, DetailedMovieActivity::class.java)
                        intent.putExtra("name", favouritesMovie.path)
                        intent.putExtra("movieID", favouritesMovie.movie_id)
                        startActivity(intent)
                    }
                })
        favouritesRecyclerView.layoutManager =  GridLayoutManager(context, 2)
        favouritesRecyclerView.isNestedScrollingEnabled = true
        favouritesRecyclerView.setHasFixedSize(false)
        favouritesRecyclerView.adapter = favouritesAdapter
    }
}