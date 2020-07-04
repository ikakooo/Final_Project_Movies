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

    }
    val searchResultMoviesLiveData: LiveData<MutableList<MovieSearchResultModelByID>> = _searchResultMoviesLiveData


}