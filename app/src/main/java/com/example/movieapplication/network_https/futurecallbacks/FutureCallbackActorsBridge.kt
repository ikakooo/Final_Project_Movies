package com.example.movieapplication.network_https.futurecallbacks

import com.example.movieapplication.bottom_navigation.actors.model.ActorsResponseModel

interface FutureCallbackActorsBridge {
    fun onResponseActor(responseModel: ActorsResponseModel)
    fun onFailure(error:String)
}