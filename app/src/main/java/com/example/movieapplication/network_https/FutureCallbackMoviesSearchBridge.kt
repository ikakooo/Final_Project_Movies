package com.example.movieapplication.network_https


import com.example.movieapplication.network_https.models.MovieSearchResultModelByID

interface FutureCallbackMoviesSearchBridge {
    fun onResponseSearchedByID(response: MovieSearchResultModelByID)
    fun onFailure(error: String)
}