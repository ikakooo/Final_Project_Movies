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

    val topTodayMoviesLiveData: LiveData<MutableList<movie>> = _topTodayMoviesLiveData

    val popularMoviesLiveData: LiveData<MutableList<movie>> = _popularMoviesLiveData


    private fun getPostsTopToday(page: String) {

        DateLoader.getRequestTopToday(
            HomeFragment.API_KEY, page,

            object : FutureCallbackCountryBridge {
                override fun onResponse(response: MainMovieModel) {
                    Log.d("dsfdfsdf", response.results[0].original_title)


                    _popularMoviesLiveData.value = response.results.toMutableList()
                    (0 until response.results.size).forEach { it ->

                    }
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
                    Log.d("dsfdfsdf", response.results[0].original_title.toString())


                    _popularMoviesLiveData.value = response.results.toMutableList()
                    (0 until response.results.size).forEach { it ->

                    }
                }

                override fun onFailure(error: String) {
                }
            }
        )
    }
}