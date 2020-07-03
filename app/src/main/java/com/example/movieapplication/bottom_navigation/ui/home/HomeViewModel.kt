package com.example.movieapplication.bottom_navigation.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.FutureCallbackCountryBridge
import com.example.movieapplication.network_https.MainMovieModel
import com.example.movieapplication.network_https.movie

class HomeViewModel : ViewModel() {


    private val _topTodayMoviesLiveData = MutableLiveData<MutableList<movie>>().apply {
        getPosts("1")
        getPosts("2")

    }

    val topTodayMoviesLiveData: LiveData<MutableList<movie>> = _topTodayMoviesLiveData


    private fun getPosts(page: String){

        DateLoader.getRequest(
            HomeFragment.API_KEY,page,

            object : FutureCallbackCountryBridge {
                override fun onResponse(response: MainMovieModel) {
                    Log.d("dsfdfsdf", response.results[0].original_title.toString())


                    _topTodayMoviesLiveData.value = response.results.toMutableList()
                    (0 until response.results.size).forEach{ it ->

                    }

                }


                override fun onFailure(error: String) {
//                    Toast.makeText(context,"this is toast message", Toast.LENGTH_SHORT).show()
//                    val toast = Toast.makeText(context, "Hello Javatpoint", Toast.LENGTH_LONG)
//                    toast.show()
//                    val myToast = Toast.makeText(context,"No Internet", Toast.LENGTH_SHORT)
//                    myToast.setGravity(Gravity.TOP,200,200)
//                    myToast.show()
                }


            }
        )

    }
}