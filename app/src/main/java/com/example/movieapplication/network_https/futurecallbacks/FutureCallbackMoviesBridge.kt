package com.example.movieapplication.network_https.futurecallbacks

import com.example.movieapplication.bottom_navigation.ui.home.models.MainMovieModel


interface FutureCallbackMoviesBridge {
    fun onResponse(response: MainMovieModel)
    fun onFailure(error: String)
}