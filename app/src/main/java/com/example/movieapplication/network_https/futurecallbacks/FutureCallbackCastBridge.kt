package com.example.movieapplication.network_https.futurecallbacks

import com.example.movieapplication.detailed_movie_view.model.MovieCastResponse

interface FutureCallbackCastBridge {
    fun onResponseCastByID(response: MovieCastResponse)
    fun onFailure(error: String)
}