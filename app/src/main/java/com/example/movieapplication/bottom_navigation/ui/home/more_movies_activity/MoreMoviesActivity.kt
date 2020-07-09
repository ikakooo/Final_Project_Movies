package com.example.movieapplication.bottom_navigation.ui.home.more_movies_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.ui.actors.ActorsAdapter
import com.example.movieapplication.bottom_navigation.ui.actors.DetailedActorsActivity
import com.example.movieapplication.bottom_navigation.ui.home.HomeFragment
import com.example.movieapplication.bottom_navigation.ui.home.adapters.TopTodayAdapter
import com.example.movieapplication.bottom_navigation.ui.home.models.MainMovieModel
import com.example.movieapplication.bottom_navigation.ui.home.models.Movies
import com.example.movieapplication.detailed_movie_view.DetailedMovieActivity
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.futurecallbacks.FutureCallbackMoviesBridge
import kotlinx.android.synthetic.main.activity_detailed_movie.*
import kotlinx.android.synthetic.main.activity_more_movies.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class MoreMoviesActivity : AppCompatActivity() {
    private var allMoviesList = mutableListOf<Movies>()
    private lateinit var allMoviesAdapter: MoreMoviesRecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_movies)
        init()
        d("fwfwefwergrreefdssdwef","intent.getStringExtra().toString()")
    }


    private fun init() {
        allMoviesAdapter = MoreMoviesRecyclerviewAdapter(allMoviesList, object : DetailedMovieListener {
                override fun detailedViewClick(position: Int) {
                    val upcomingMovie = allMoviesList[position]
                    val intent = Intent(applicationContext, DetailedMovieActivity::class.java)
                    intent.putExtra("name", upcomingMovie.original_title)
                    intent.putExtra("movieID", upcomingMovie.id)
                    startActivity(intent)
                } })
        MoreMoviesRecyclerviewID.layoutManager = GridLayoutManager(this, 2)
        MoreMoviesRecyclerviewID.adapter = allMoviesAdapter


        d("fwfwefwergrreefwef",intent.getStringExtra("ContentName").toString())

        when (intent.extras!!.getString("ContentName", "AllTopToday")) {
            "AllTopToday" -> {
                getPostsTopToday()

            }
            "AllPopular" -> {
                getPostsPopular()
            }
            "AllTopRated" -> {
                getPostsTopRated()
            }
            "AllUpComing" -> {
                getPostsUpComing()
            }
        }

d("fwfwefwefwef",intent.extras!!.getString("ContentName", "AllTopToday"))

    }



    private fun getPostsTopToday() {
        DateLoader.getRequestTopToday(
            HomeFragment.API_KEY, "1",
            object : FutureCallbackMoviesBridge {
                override fun onResponse(response: MainMovieModel) {
                    d("jfkefjerfjerk", response.results.toString())
                    allMoviesList.addAll( response.results.toMutableList())
                }
                override fun onFailure(error: String) {
                } })
    }


    private fun getPostsPopular() {

        DateLoader.getRequestPopular(
            HomeFragment.API_KEY, "1",

            object :
                FutureCallbackMoviesBridge {

                override fun onResponse(response: MainMovieModel) {
                    allMoviesList = response.results.toMutableList()
                }
                override fun onFailure(error: String) {
                }
            }
        )
    }

    private fun getPostsTopRated() {
        DateLoader.getRequestTopRated(
            HomeFragment.API_KEY, "1",
            object :
                FutureCallbackMoviesBridge {
                override fun onResponse(response: MainMovieModel) {
                    allMoviesList = response.results.toMutableList()
                }
                override fun onFailure(error: String) {
                }
            }
        )
    }


    private fun getPostsUpComing() {
        DateLoader.getRequestUpComing(
            HomeFragment.API_KEY, "1",
            object :
                FutureCallbackMoviesBridge {
                override fun onResponse(response: MainMovieModel) {
                    allMoviesList = response.results.toMutableList()
                }

                override fun onFailure(error: String) {}
            })
    }

}