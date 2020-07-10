package com.example.movieapplication.network_https.futurecallbacks

import com.example.movieapplication.bottom_navigation.actors.ActorsResponse

interface FutureCallbackActorsBridge {
    fun onResponseActor(response: ActorsResponse)
    fun onFailure(error:String)
}