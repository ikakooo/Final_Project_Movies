package com.example.movieapplication.network_https

import com.example.movieapplication.bottom_navigation.ui.search.models.ByNameSearchResultModel


interface FutureCallbackMoviesSearchByNameBridge {
    fun onResponseSearchedByName(response: ByNameSearchResultModel)
    fun onFailure(error: String)
}