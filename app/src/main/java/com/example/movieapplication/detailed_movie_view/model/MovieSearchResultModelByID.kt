package com.example.movieapplication.detailed_movie_view.model


data class MovieSearchResultModelByID(
    val id: String,
    val genres: ArrayList<Genre>,
    val poster_path: String,
    val backdrop_path: String,
    val original_title: String,
    val overview: String,
    val release_date: String,
    val tagline: String,
    val vote_average: String


) {

    data class Genre(
        val name: String
    )
}