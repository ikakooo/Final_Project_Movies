package com.example.movieapplication.bottom_navigation.home.more_movies_activity

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.home.HomeFragment
import com.example.movieapplication.bottom_navigation.home.models.MainMovieModel
import com.example.movieapplication.bottom_navigation.home.models.Movies
import com.example.movieapplication.detailed_movie_view.DetailedMovieActivity
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.futurecallbacks.FutureCallbackMoviesBridge
import kotlinx.android.synthetic.main.activity_more_movies.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MoreMoviesActivity : AppCompatActivity() {
    private var allMoviesList = mutableListOf<Movies>()
    private lateinit var allMoviesAdapter: MoreMoviesRecyclerviewAdapter
    private var pagesCountForAddingItems = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_movies)
        init()
    }


    private fun init() {

        allMoviesAdapter = MoreMoviesRecyclerviewAdapter(allMoviesList, object : DetailedMovieListener {
                override fun detailedViewClick(position: Int) {
                    val upcomingMovie = allMoviesList[position]
                    val intent = Intent(applicationContext, DetailedMovieActivity::class.java)
                    intent.putExtra("name", upcomingMovie.original_title)
                    intent.putExtra("movieID", upcomingMovie.id)
                    startActivity(intent)
                }})
        MoreMoviesRecyclerviewID.layoutManager = GridLayoutManager(this, 2)
        MoreMoviesRecyclerviewID.isNestedScrollingEnabled = true
        MoreMoviesRecyclerviewID.setHasFixedSize(false)
        MoreMoviesRecyclerviewID.adapter = allMoviesAdapter

        dynamicHttpRequesting(pagesCountForAddingItems.toString())
        pagesCountForAddingItems++


        MoreMoviesRecyclerviewID.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    dynamicHttpRequesting(pagesCountForAddingItems.toString())
                    pagesCountForAddingItems++

                }
            }
        })



    }
    private fun dynamicHttpRequesting(page:String){
        when (intent.extras!!.getString("ContentName", "AllTopToday")) {
            "AllTopToday" -> {
                getPostsTopToday(page)

            }
            "AllPopular" -> {
                getPostsPopular(page)
            }
            "AllTopRated" -> {
                getPostsTopRated(page)
            }
            "AllUpComing" -> {
                getPostsUpComing(page)
            }
        }
    }


    private fun getPostsTopToday(page:String) {
        DateLoader.getRequestTopToday(
            HomeFragment.API_KEY, page,
            object : FutureCallbackMoviesBridge {
                override fun onResponse(response: MainMovieModel) {
                    d("jfkefjerfjerk", response.results.toString())
                    allMoviesList.addAll( response.results.toMutableList())
                    allMoviesAdapter.notifyDataSetChanged()
                }
                override fun onFailure(error: String) {
                } })
    }
    private fun getPostsPopular(page:String) {

        DateLoader.getRequestPopular(
            HomeFragment.API_KEY, page,

            object :
                FutureCallbackMoviesBridge {

                override fun onResponse(response: MainMovieModel) {
                    allMoviesList.addAll( response.results.toMutableList())
                    allMoviesAdapter.notifyDataSetChanged()
                }
                override fun onFailure(error: String) {
                }
            }
        )
    }
    private fun getPostsTopRated(page:String) {
        DateLoader.getRequestTopRated(
            HomeFragment.API_KEY, page,
            object :
                FutureCallbackMoviesBridge {
                override fun onResponse(response: MainMovieModel) {
                    allMoviesList.addAll( response.results.toMutableList())
                    allMoviesAdapter.notifyDataSetChanged()
                }
                override fun onFailure(error: String) {
                }
            }
        )
    }
    private fun getPostsUpComing(page:String) {
        DateLoader.getRequestUpComing(
            HomeFragment.API_KEY, page,
            object :
                FutureCallbackMoviesBridge {
                override fun onResponse(response: MainMovieModel) {
                    allMoviesList.addAll( response.results.toMutableList())
                    allMoviesAdapter.notifyDataSetChanged()
                }

                override fun onFailure(error: String) {}
            })
    }

}