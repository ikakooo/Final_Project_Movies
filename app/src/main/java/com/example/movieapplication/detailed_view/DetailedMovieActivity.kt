package com.example.movieapplication.detailed_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.ui.home.HomeFragment
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.FutureCallbackMoviesBridge
import com.example.movieapplication.network_https.models.MainMovieModel
import com.example.movieapplication.network_https.models.MovieSearchResultModelByID
import kotlinx.android.synthetic.main.activity_detailed_movie.*

class DetailedMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_movie)
        init()
    }
    private fun init(){
       // val movieID = intent.getStringExtra("movieID")?.toInt()
        val originalTitle = intent.getStringExtra("name")
        titleTV.text = originalTitle
        getPostsDetailedMovie(30)
    }

    private fun getPostsDetailedMovie(page: Int) {
        DateLoader.getRequestedMovieByID(
            1, HomeFragment.API_KEY,
            object : FutureCallbackMoviesBridge {
                override fun onResponseSearchedByID(response: MovieSearchResultModelByID) {
                    d("detailedResponse", response.toString())
                }

                override fun onResponse(response: MainMovieModel) {
                    /////////////////ცაარიელი უნდა იყოს
                }
                override fun onFailure(error: String) {
                    d("detailedResponse", error)
                }
            }
        )
    }
}