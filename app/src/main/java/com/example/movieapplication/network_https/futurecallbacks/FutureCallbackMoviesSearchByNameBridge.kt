package com.example.movieapplication.network_https.futurecallbacks

import com.example.movieapplication.bottom_navigation.search.models.SearchResultModelByName


interface FutureCallbackMoviesSearchByNameBridge {
    fun onResponseSearchedByName(response: SearchResultModelByName)
    fun onFailure(error: String)
}