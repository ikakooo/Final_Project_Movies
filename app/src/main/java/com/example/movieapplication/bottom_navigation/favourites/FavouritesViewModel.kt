package com.example.movieapplication.bottom_navigation.favourites

import android.annotation.SuppressLint
import android.util.Log
import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.home.HomeFragment
import com.example.movieapplication.bottom_navigation.home.models.Movies
import com.example.movieapplication.detailed_movie_view.model.MovieSearchResultModelByID
import com.example.movieapplication.local_data_base.DatabaseBuilder
import com.example.movieapplication.local_data_base.RoomFavouriteMovieModel
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.futurecallbacks.FutureCallbackMoviesSearchByIDBridge
import kotlinx.android.synthetic.main.activity_detailed_movie.*
import java.util.logging.Handler

class FavouritesViewModel : ViewModel() {

    private val dB = DatabaseBuilder.roomDB.favoriteDaoConnection().getFavouriteMovies()
    private val _favouritesMoviesLiveData = MutableLiveData<MutableList<RoomFavouriteMovieModel>>().apply {

        value =dB.toMutableList()
    }

//    private var moviesID = mutableListOf<String>()
//    private var movies = mutableListOf<Movies>()
    val favouritesMoviesLiveData: LiveData<MutableList<RoomFavouriteMovieModel>> = _favouritesMoviesLiveData


    private fun getPostsFavouritesFromRoomDB() {
       // d("btAMrtbfddvfdIfbOZ", dB.toString())
//        (dB.indices).forEach {
//            //moviesID.add(it,dB[it].movie_id)
//            movies.add(Movies(dB[it].id.toString(),dB[it].movie_id.toString(),dB[it].path.toString(),dB[it].path.toString()))
//        }
       /// movies.add(Movies("evrer","vreeve","vererv","reverv"))



        //d("btAMrtbfdvfdIfbOZ", dB.toString())
    }
}