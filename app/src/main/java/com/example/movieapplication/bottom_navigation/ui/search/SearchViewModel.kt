package com.example.movieapplication.bottom_navigation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.bottom_navigation.ui.home.HomeFragment
import com.example.movieapplication.bottom_navigation.ui.home.HomeFragment.Companion.API_KEY
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.FutureCallbackMoviesBridge
import com.example.movieapplication.network_https.models.MainMovieModel
import com.example.movieapplication.network_https.models.MovieSearchResultModelByID
import com.example.movieapplication.network_https.models.movie

class SearchViewModel : ViewModel() {

    private val _searchResultMoviesLiveData = MutableLiveData<MutableList<MovieSearchResultModelByID>>().apply {
        getPostsTopToday(1)
    }
    val searchResultMoviesLiveData: LiveData<MutableList<MovieSearchResultModelByID>> = _searchResultMoviesLiveData


    private fun getPostsTopToday(page: Int) {
        DateLoader.getRequestedMovieByID(
            1,API_KEY,
            object : FutureCallbackMoviesBridge {
                override fun onResponseSearchedByID(response: MovieSearchResultModelByID) {

                    _searchResultMoviesLiveData.value = response
                }

                override fun onResponse(response: MainMovieModel) {
                    /////////////////ცაარიელი უნდა იყოს
                }
                override fun onFailure(error: String) {
                }
            }
        )
    }
}