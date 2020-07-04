package com.example.movieapplication.detailed_view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.ui.home.HomeFragment
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.FutureCallbackMoviesSearchBridge
import com.example.movieapplication.network_https.models.MovieSearchResultModelByID
import kotlinx.android.synthetic.main.activity_detailed_movie.*


class DetailedMovieActivity : AppCompatActivity() {
    val imgBaseURL = "https://image.tmdb.org/t/p/w780/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_movie)
        init()
    }
    private fun init(){
        var movieID = intent.getStringExtra("movieID")
        d("sdfdfdsf", movieID.toString())
        val originalTitle = intent.getStringExtra("name")
        titleTV.text = originalTitle
        if (movieID == null) { movieID="531454" }

        getPostsDetailedMovie(movieID.toInt())
    }

    private fun getPostsDetailedMovie(id: Int) {
        DateLoader.getRequestedMovieByID(
            id, HomeFragment.API_KEY,
            object : FutureCallbackMoviesSearchBridge {
                @SuppressLint("SetTextI18n")
                override fun onResponseSearchedByID(response: MovieSearchResultModelByID) {
                    d("fsfesefesfsf", response.toString())
                    titleDetailedTextViewID.text = response.overview
                    titleTV.text = response.original_title
                    ratingTV.text = response.vote_average+"/10"
                    (0 until response.genres.size).forEach{
                        val text = genreTVID.text.toString()
                        genreTVID.text = text + " " + response.genres[it].name
                    }

                    Glide.with(applicationContext).load(imgBaseURL + response.poster_path).into(moviesDetailedImageViewID)
                    Glide.with(applicationContext).load(imgBaseURL + response.backdrop_path).into(detailedBackground)
                }
                override fun onFailure(error: String) {
                    d("detailedResponse", error)

                }
            }
        )
    }
}