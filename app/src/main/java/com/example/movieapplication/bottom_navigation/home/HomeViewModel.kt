package com.example.movieapplication.bottom_navigation.home

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.futurecallbacks.FutureCallbackMoviesBridge
import com.example.movieapplication.bottom_navigation.home.models.MainMovieModel
import com.example.movieapplication.bottom_navigation.home.models.Movies

class HomeViewModel : ViewModel() {


    private val _topTodayMoviesLiveData = MutableLiveData<MutableList<Movies>>().apply {
        getPostsTopToday()
    }
    private val _popularMoviesLiveData = MutableLiveData<MutableList<Movies>>().apply {
        getPostsPopular()
    }
    private val _topRatedMoviesLiveData = MutableLiveData<MutableList<Movies>>().apply {
        getPostsTopRated()
    }
    private val _upComingMoviesLiveData = MutableLiveData<MutableList<Movies>>().apply {
        getPostsUpComing()
    }

    val topTodayMoviesLiveData: LiveData<MutableList<Movies>> = _topTodayMoviesLiveData
    val topRatedMoviesLiveData: LiveData<MutableList<Movies>> = _topRatedMoviesLiveData
    val popularMoviesLiveData: LiveData<MutableList<Movies>> = _popularMoviesLiveData
    val upComingMoviesLiveData: LiveData<MutableList<Movies>> = _upComingMoviesLiveData


    private fun getPostsTopToday() {
        DateLoader.getRequestTopToday(
            HomeFragment.API_KEY, "1",
            object : FutureCallbackMoviesBridge {
                override fun onResponse(response: MainMovieModel) {
                    _topTodayMoviesLiveData.value = response.results.toMutableList()
                    d("cfdvdfvfdv",response.results.toMutableList().toString())
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
                    _popularMoviesLiveData.value = response.results.toMutableList()

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
                    _topRatedMoviesLiveData.value = response.results.toMutableList()
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
                    _upComingMoviesLiveData.value = response.results.toMutableList()

                }

                override fun onFailure(error: String) {}
            })
    }


}