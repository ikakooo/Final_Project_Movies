package com.example.movieapplication.network_https.models

data class MainMovieModel(
    val page : String,
    val total_pages : String,
    val results : ArrayList<movie>
)

data class movie(
    var id : String,
    var original_title : String,
    var poster_path : String,
    var backdrop_path:String
)






data class similarresonse(
    val page:Int=0,
    val results:ArrayList<similar>

)
data class similar(
    val cast_id:Int,
    val id: Int,
    val original_title: String,
    val poster_path:String
)
