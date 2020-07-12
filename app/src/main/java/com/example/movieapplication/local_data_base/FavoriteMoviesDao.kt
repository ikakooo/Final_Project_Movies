package com.example.movieapplication.local_data_base

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoomFavouriteMovieModel(favourite: RoomFavouriteMovieModel)

    @Query("select * from favourite_movies")
    fun getFavouriteMovies(): List<RoomFavouriteMovieModel>

    @Query("delete from favourite_movies where movie_id = :id")
    fun deleteFavouriteMovie(id: String)

    @Query("Select * from favourite_movies where movie_id =  :id")
    fun isFavourite(id: String): RoomFavouriteMovieModel


}