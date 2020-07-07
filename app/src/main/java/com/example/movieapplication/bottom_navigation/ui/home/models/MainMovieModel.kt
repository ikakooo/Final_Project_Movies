package com.example.movieapplication.bottom_navigation.ui.home.models

data class MainMovieModel(
    val page : String,
    val total_pages : String,
    val results : ArrayList<Movies>
)

