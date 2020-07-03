package com.example.movieapplication.bottom_navigation.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.FutureCallbackCountryBridge
import com.example.movieapplication.network_https.MainMovieModel
import com.example.movieapplication.network_https.movie

class HomeViewModel : ViewModel() {


    private val _topTodayMoviesLiveData = MutableLiveData<MutableList<movie>>().apply {
        getPostsTopToday("1")
    }
    private val _popularMoviesLiveData = MutableLiveData<MutableList<movie>>().apply {
        getPostsPopular("1")
    }
    private val _topRatedMoviesLiveData = MutableLiveData<MutableList<movie>>().apply {
        getPostsTopRated("1")
    }
    private val _upComingMoviesLiveData = MutableLiveData<MutableList<movie>>().apply {
        getPostsUpComing("1")
    }

    val topTodayMoviesLiveData: LiveData<MutableList<movie>> = _topTodayMoviesLiveData
    val topRatedMoviesLiveData: LiveData<MutableList<movie>> = _topRatedMoviesLiveData
    val popularMoviesLiveData: LiveData<MutableList<movie>> = _popularMoviesLiveData
    val upComingMoviesLiveData: LiveData<MutableList<movie>> = _upComingMoviesLiveData


    private fun getPostsTopToday(page: String) {
        DateLoader.getRequestTopToday(
            HomeFragment.API_KEY, page,
            object : FutureCallbackCountryBridge {
                override fun onResponse(response: MainMovieModel) {
                    _topTodayMoviesLiveData.value = response.results.toMutableList()
                }
                override fun onFailure(error: String) {
                }
            }
        )
    }


    private fun getPostsPopular(page: String) {

        DateLoader.getRequestPopular(
            HomeFragment.API_KEY, page,

            object : FutureCallbackCountryBridge {
                override fun onResponse(response: MainMovieModel) {
                    _popularMoviesLiveData.value = response.results.toMutableList()
                }
                override fun onFailure(error: String) {
                }
            }
        )
    }

    private fun getPostsTopRated(page: String) {
        DateLoader.getRequestTopRated(
            HomeFragment.API_KEY, page,
            object : FutureCallbackCountryBridge {
                override fun onResponse(response: MainMovieModel) {
                    _topRatedMoviesLiveData.value = response.results.toMutableList()
                }
                override fun onFailure(error: String) {
                }
            }
        )
    }


    private fun getPostsUpComing(page: String) {
        DateLoader.getRequestUpComing(
            HomeFragment.API_KEY, page,
            object : FutureCallbackCountryBridge {
                override fun onResponse(response: MainMovieModel) {
                    _upComingMoviesLiveData.value = response.results.toMutableList()
                }

                override fun onFailure(error: String) {}
            })
    }


}