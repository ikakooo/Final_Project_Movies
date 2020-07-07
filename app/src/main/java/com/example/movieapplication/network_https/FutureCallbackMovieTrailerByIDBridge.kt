package com.example.movieapplication.network_https

import com.example.movieapplication.detailed_movie_view.model.MovieTrailerModeByID

interface FutureCallbackMovieTrailerByIDBridge {
    fun onResponseMovieTrailerByID(response: MovieTrailerModeByID)
    fun onFailure(error: String)
}