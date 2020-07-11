package com.example.movieapplication.bottom_navigation.actors

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.bottom_navigation.home.HomeFragment
import com.example.movieapplication.network_https.futurecallbacks.FutureCallbackActorsBridge
import com.example.movieapplication.network_https.DateLoader

class ActorsViewModel : ViewModel() {

    private var page = 1

    private val _popularActorsLiveData = MutableLiveData<MutableList<ActorsResponse.Actor>>().apply {
        getPopularActors(page.toString())
    }


    val popularActorsLiveData:LiveData<MutableList<ActorsResponse.Actor>> = _popularActorsLiveData

    private fun getPopularActors(page:String){
        DateLoader.getPopularActors(page, HomeFragment.API_KEY, object :
            FutureCallbackActorsBridge {
            override fun onResponseActor(response: ActorsResponse) {
                d("successResponse", response.toString())
                _popularActorsLiveData.value = response.results.toMutableList()

            }

            override fun onFailure(error: String) {
                d("failResponse", error)
            }

        })
    }
}