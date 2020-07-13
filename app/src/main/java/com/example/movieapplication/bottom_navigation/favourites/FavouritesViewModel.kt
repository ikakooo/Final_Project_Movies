package com.example.movieapplication.bottom_navigation.favourites

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.local_data_base.DatabaseBuilder
import com.example.movieapplication.local_data_base.RoomFavouriteMovieModel


class FavouritesViewModel : ViewModel() {


    private val _favouritesMoviesLiveData = MutableLiveData<MutableList<RoomFavouriteMovieModel>>().apply {

        value = DatabaseBuilder.roomDB.favoriteDaoConnection().getFavouriteMovies().toMutableList()
        d("jdsncsckj",DatabaseBuilder.roomDB.favoriteDaoConnection().getFavouriteMovies().toMutableList().toString())
        d("fervrev",value.toString())
    }
    val favouritesMoviesLiveData: LiveData<MutableList<RoomFavouriteMovieModel>> = _favouritesMoviesLiveData

}