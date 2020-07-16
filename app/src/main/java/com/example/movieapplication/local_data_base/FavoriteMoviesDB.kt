package com.example.movieapplication.local_data_base

import androidx.room.*

@Database(entities = [RoomFavouriteMovieModel::class], version = 2)
abstract class FavoriteMoviesDB: RoomDatabase(){
    abstract fun favoriteDaoConnection() : FavoriteMoviesDao
}