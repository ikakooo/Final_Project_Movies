package com.example.movieapplication.local_data_base

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MoviesDao {

    @Query("select * from favourite_movies")
    fun getFavouriteMovies(): List<RoomFavouriteMovieModel>

    @Query("delete from favourite_movies where movie_id = :id")
    fun deleteFavouriteMovie(id: String)

    @Insert
    fun insertMovie(favourite: RoomFavouriteMovieModel)

    @Query("Select * from favourite_movies where movie_id =  :id")
    fun isFavourite(id: String): RoomFavouriteMovieModel


}