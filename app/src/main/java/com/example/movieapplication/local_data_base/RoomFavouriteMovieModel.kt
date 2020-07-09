package com.example.movieapplication.local_data_base

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
data class RoomFavouriteMovieModel (
    @PrimaryKey(autoGenerate = true) val id : Long?=null,
    @ColumnInfo(name = "movie_id") var movie_id : String,
    @ColumnInfo(name = "path") var path:String
)