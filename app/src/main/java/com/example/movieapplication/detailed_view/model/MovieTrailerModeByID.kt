package com.example.movieapplication.detailed_view.model

data class MovieTrailerModeByID(
    val results : ArrayList<TrailerVideo>
){

    data class TrailerVideo(
        val id: String,
        val key : String,
        val name : String
    )

}
