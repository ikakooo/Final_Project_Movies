package com.example.movieapplication.bottom_navigation.ui.actors

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.bottom_navigation.ui.home.HomeFragment
import com.example.movieapplication.network_https.ActorsCallback
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.models.movie

class ActorsViewModel : ViewModel() {


    private val _popularActorsLiveData = MutableLiveData<MutableList<ActorsResponse.Actor>>().apply {
        getPopularActors("1")
    }

    val popularActorsLiveData:LiveData<MutableList<ActorsResponse.Actor>> = _popularActorsLiveData

    private fun getPopularActors(page:String){
        DateLoader.getPopularActors(page, HomeFragment.API_KEY, object :ActorsCallback{
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