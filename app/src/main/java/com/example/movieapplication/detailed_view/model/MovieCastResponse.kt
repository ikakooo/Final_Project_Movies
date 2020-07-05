package com.example.movieapplication.detailed_view.model


data class MovieCastResponse(
    val id:Int=0,
    val cast:ArrayList<MovieCast>?

){
    data class MovieCast(
        val cast_id:Int,
        val name: String,
        val profile_path:String,
        var id: String
    )
}
