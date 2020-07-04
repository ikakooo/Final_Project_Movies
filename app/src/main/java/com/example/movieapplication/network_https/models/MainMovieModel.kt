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





data class moviecastresopnse(
    val id:Int=0,
    val cast:ArrayList<moviecast>

)
data class moviecast(
    val cast_id:Int,
    val name: String,
    val profile_path:String,
    var id: String
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
data class videoresponse(
    val results : ArrayList<video>
)

data class video(
    val id: String,
    val key : String,
    val name : String
)