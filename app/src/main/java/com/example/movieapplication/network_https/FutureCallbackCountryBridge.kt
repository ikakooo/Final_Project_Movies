package com.example.movieapplication.network_https

interface FutureCallbackCountryBridge {
    fun onResponse(response: MainMovieModel)
    fun onFailure(error: String)
}