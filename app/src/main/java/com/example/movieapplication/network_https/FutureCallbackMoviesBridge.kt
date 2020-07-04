package com.example.movieapplication.network_https

import com.example.movieapplication.network_https.models.MainMovieModel
import com.example.movieapplication.network_https.models.MovieSearchResultModelByID

interface FutureCallbackMoviesBridge {
    fun onResponseSearchedByID(response: MovieSearchResultModelByID)
    fun onResponse(response: MainMovieModel)
    fun onFailure(error: String)
}