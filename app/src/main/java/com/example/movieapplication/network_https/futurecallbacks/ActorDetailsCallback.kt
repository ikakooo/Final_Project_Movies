package com.example.movieapplication.network_https.futurecallbacks

import com.example.movieapplication.bottom_navigation.actors.ActorsResponse

interface ActorDetailsCallback {
    fun onResponseActorDetail(response: ActorsResponse.Actor)
    fun onFailure(error:String)
}