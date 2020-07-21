package com.example.movieapplication.bottom_navigation.actors

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.bottom_navigation.actors.model.ActorsResponseModel
import com.example.movieapplication.bottom_navigation.home.HomeFragment
import com.example.movieapplication.constants.Constants.API_KEY
import com.example.movieapplication.network_https.futurecallbacks.FutureCallbackActorsBridge
import com.example.movieapplication.network_https.DataLoader

class ActorsViewModel : ViewModel() {

    private var page = 1

    private val _popularActorsLiveData = MutableLiveData<MutableList<ActorsResponseModel.Actor>>().apply {
        getPopularActors(page.toString())
    }


    val popularActorsLiveDataModel:LiveData<MutableList<ActorsResponseModel.Actor>> = _popularActorsLiveData

    private fun getPopularActors(page:String){
        DataLoader.getPopularActors(page, API_KEY, object :
            FutureCallbackActorsBridge {
            override fun onResponseActor(responseModel: ActorsResponseModel) {
                d("successResponse", responseModel.toString())
                _popularActorsLiveData.value = responseModel.results.toMutableList()

            }

            override fun onFailure(error: String) {
                d("failResponse", error)
            }

        })
    }
}