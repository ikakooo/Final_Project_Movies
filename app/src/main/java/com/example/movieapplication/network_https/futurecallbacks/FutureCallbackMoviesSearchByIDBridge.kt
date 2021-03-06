package com.example.movieapplication.network_https.futurecallbacks


import com.example.movieapplication.detailed_movie_view.model.MovieSearchResultModelByID

interface FutureCallbackMoviesSearchByIDBridge {
    fun onResponseSearchedByID(response: MovieSearchResultModelByID)
    fun onFailure(error: String)
}