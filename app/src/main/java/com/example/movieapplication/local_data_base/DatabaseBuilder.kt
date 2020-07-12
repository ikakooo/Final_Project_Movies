package com.example.movieapplication.local_data_base

import androidx.room.Room
import com.example.movieapplication.AppRoot

object DatabaseBuilder {
    val roomDB by lazy {
        Room.databaseBuilder(
            AppRoot.instance.getContext(),
            FavoriteMoviesDB::class.java,
            "Fav.roomDB"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

}