package com.example.movieapplication.network_https

import com.example.movieapplication.bottom_navigation.ui.actors.ActorsResponse

interface ActorsCallback {
    fun onResponseActor(response: ActorsResponse)
    fun onFailure(error:String)
}