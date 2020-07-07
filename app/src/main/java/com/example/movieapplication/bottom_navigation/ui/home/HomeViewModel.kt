package com.example.movieapplication.bottom_navigation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.futurecallbacks.FutureCallbackMoviesBridge
import com.example.movieapplication.bottom_navigation.ui.home.models.MainMovieModel
import com.example.movieapplication.bottom_navigation.ui.home.models.Movies

class HomeViewModel : ViewModel() {


    private val _topTodayMoviesLiveData = MutableLiveData<MutableList<Movies>>().apply {
        getPostsTopToday("1")
    }
    private val _popularMoviesLiveData = MutableLiveData<MutableList<Movies>>().apply {
        getPostsPopular("1")
    }
    private val _topRatedMoviesLiveData = MutableLiveData<MutableList<Movies>>().apply {
        getPostsTopRated("1")
    }
    private val _upComingMoviesLiveData = MutableLiveData<MutableList<Movies>>().apply {
        getPostsUpComing("1")
    }

    val topTodayMoviesLiveData: LiveData<MutableList<Movies>> = _topTodayMoviesLiveData
    val topRatedMoviesLiveData: LiveData<MutableList<Movies>> = _topRatedMoviesLiveData
    val popularMoviesLiveData: LiveData<MutableList<Movies>> = _popularMoviesLiveData
    val upComingMoviesLiveData: LiveData<MutableList<Movies>> = _upComingMoviesLiveData


    private fun getPostsTopToday(page: String) {
        DateLoader.getRequestTopToday(
            HomeFragment.API_KEY, page,
            object :
                FutureCallbackMoviesBridge {
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

    private fun getPostsTopRated(page: String) {
        DateLoader.getRequestTopRated(
            HomeFragment.API_KEY, page,
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


    private fun getPostsUpComing(page: String) {
        DateLoader.getRequestUpComing(
            HomeFragment.API_KEY, page,
            object :
                FutureCallbackMoviesBridge {
                override fun onResponse(response: MainMovieModel) {
                    _upComingMoviesLiveData.value = response.results.toMutableList()
                }

                override fun onFailure(error: String) {}
            })
    }


}