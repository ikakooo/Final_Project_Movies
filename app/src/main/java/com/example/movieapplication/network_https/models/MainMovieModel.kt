package com.example.movieapplication.network_https.models

data class MainMovieModel(
    val page : String,
    val total_pages : String,
    val results : ArrayList<Movies>
)

