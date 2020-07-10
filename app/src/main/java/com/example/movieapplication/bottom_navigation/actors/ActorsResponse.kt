package com.example.movieapplication.bottom_navigation.actors

data class ActorsResponse(
    val page: String,
    var results: ArrayList<Actor>
) {
    data class Actor(var id : String,
                     var name : String,
                     var profile_path : String,
                     var popularity:Number,
                     var birthday:String,
                     var biography:String)
}
