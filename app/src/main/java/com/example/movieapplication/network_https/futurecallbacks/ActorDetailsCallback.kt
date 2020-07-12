package com.example.movieapplication.network_https.futurecallbacks

import com.example.movieapplication.bottom_navigation.actors.model.ActorsResponseModel

interface ActorDetailsCallback {
    fun onResponseActorDetail(responseModel: ActorsResponseModel.Actor)
    fun onFailure(error:String)
}