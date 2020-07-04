package com.example.movieapplication.network_https

import com.example.movieapplication.network_https.models.MainMovieModel


interface FutureCallbackMoviesBridge {
    fun onResponse(response: MainMovieModel)
    fun onFailure(error: String)
}