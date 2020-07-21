package com.example.movieapplication.bottom_navigation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.home.adapters.PopularAdapter
import com.example.movieapplication.bottom_navigation.home.adapters.TopRatedAdapter
import com.example.movieapplication.bottom_navigation.home.adapters.TopTodayAdapter
import com.example.movieapplication.bottom_navigation.home.adapters.UpcomingAdapter
import com.example.movieapplication.bottom_navigation.home.models.Movies
import com.example.movieapplication.bottom_navigation.home.more_movies_activity.MoreMoviesActivity
import com.example.movieapplication.detailed_movie_view.DetailedMovieActivity
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private var topTodayMoviesList = mutableListOf<Movies>()
    private lateinit var topTodayAdapter: TopTodayAdapter

    private var popularMoviesList = mutableListOf<Movies>()
    private lateinit var popularAdapter: PopularAdapter

    private var topRatedMoviesList = mutableListOf<Movies>()
    private lateinit var topRatedAdapter: TopRatedAdapter

    private var upComingMoviesList = mutableListOf<Movies>()
    private lateinit var upComingAdapter: UpcomingAdapter

    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        init(root)
        goToMoreMoviesOnClick(root)

        homeViewModel.topTodayMoviesLiveData.observe(viewLifecycleOwner, Observer {
            topTodayMoviesList.addAll(it)
            topTodayAdapter.notifyDataSetChanged()
        })

        homeViewModel.popularMoviesLiveData.observe(viewLifecycleOwner, Observer {

            popularMoviesList.addAll(it)
            popularAdapter.notifyDataSetChanged()
        })

        homeViewModel.topRatedMoviesLiveData.observe(viewLifecycleOwner, Observer {

            topRatedMoviesList.addAll(it)
            topRatedAdapter.notifyDataSetChanged()
        })

        homeViewModel.upComingMoviesLiveData.observe(viewLifecycleOwner, Observer {

            upComingMoviesList.addAll(it)
            root.progressBarVisibilityID.isVisible = false
            upComingAdapter.notifyDataSetChanged()

        })

        return root
    }

    private fun init(root: View) {
        /////////////////////////////////////////////////////////
        topTodayAdapter =
            TopTodayAdapter(
                topTodayMoviesList,
                object : DetailedMovieListener {
                    override fun detailedViewClick(position: Int) {
                        val topTodayMovie = topTodayMoviesList[position]
                        val intent = Intent(context, DetailedMovieActivity::class.java)
                        intent.putExtra("name", topTodayMovie.original_title)
                        intent.putExtra("movieID", topTodayMovie.id)
                        startActivity(intent)
                    }

                })
        root.topTodayRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        root.topTodayRecyclerView.isNestedScrollingEnabled = true
        root.topTodayRecyclerView.setHasFixedSize(false)
        root.topTodayRecyclerView.adapter = topTodayAdapter
/////////////////////////////////////////////////////////////////////////////////////////////////
        topRatedAdapter = TopRatedAdapter(topRatedMoviesList, object : DetailedMovieListener {
            override fun detailedViewClick(position: Int) {
                val topRatedMovie = topRatedMoviesList[position]
                val intent = Intent(context, DetailedMovieActivity::class.java)
                intent.putExtra("name", topRatedMovie.original_title)
                intent.putExtra("movieID", topRatedMovie.id)
                startActivity(intent)
            }

        })
        root.topRatedRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        root.topRatedRecyclerView.isNestedScrollingEnabled = true
        root.topRatedRecyclerView.setHasFixedSize(false)
        root.topRatedRecyclerView.adapter = topRatedAdapter
///////////////////////////////////////////////////////////////////////////////////////////
        popularAdapter = PopularAdapter(popularMoviesList, object : DetailedMovieListener {
            override fun detailedViewClick(position: Int) {
                val popularMovie = popularMoviesList[position]
                val intent = Intent(context, DetailedMovieActivity::class.java)
                intent.putExtra("name", popularMovie.original_title)
                intent.putExtra("movieID", popularMovie.id)
                startActivity(intent)
            }

        })
        root.popularRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        root.popularRecyclerView.isNestedScrollingEnabled = false
        root.popularRecyclerView.setHasFixedSize(false)
        root.popularRecyclerView.adapter = popularAdapter
////////////////////////////////////////////////////////////////////////////
        upComingAdapter = UpcomingAdapter(upComingMoviesList, object : DetailedMovieListener {
            override fun detailedViewClick(position: Int) {
                val upcomingMovie = upComingMoviesList[position]
                val intent = Intent(context, DetailedMovieActivity::class.java)
                intent.putExtra("name", upcomingMovie.original_title)
                intent.putExtra("movieID", upcomingMovie.id)
                startActivity(intent)
            }
        })
        root.upcomingRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        root.upcomingRecyclerView.isNestedScrollingEnabled = false
        root.upcomingRecyclerView.setHasFixedSize(false)
        root.upcomingRecyclerView.adapter = upComingAdapter


    }


    private fun goToMoreMoviesOnClick(root: View) {
        fun ImageView.customOnClickListener(ContentNameString: String) {
            setOnClickListener {
                val intent = Intent(context, MoreMoviesActivity::class.java)
                intent.putExtra("ContentName", ContentNameString)
                startActivity(intent)

            }

        }
        root.AllTopTodayButtonID.customOnClickListener("AllTopToday")
        root.AllPopularButtonID.customOnClickListener("AllPopular")
        root.AllTopRatedButtonID.customOnClickListener("AllTopRated")
        root.AllUpComingButtonID.customOnClickListener("AllUpComing")
    }


}