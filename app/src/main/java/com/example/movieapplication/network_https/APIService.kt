package com.example.movieapplication.network_https

import com.example.movieapplication.bottom_navigation.ui.actors.ActorsResponse
import com.example.movieapplication.bottom_navigation.ui.home.models.MainMovieModel
import com.example.movieapplication.bottom_navigation.ui.search.models.ByNameSearchResultModel
import com.example.movieapplication.detailed_movie_view.model.MovieCastResponse
import com.example.movieapplication.detailed_movie_view.model.MovieSearchResultModelByID
import com.example.movieapplication.detailed_movie_view.model.MovieTrailerModeByID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("3/movie/popular")
    fun getPopular(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): Call<MainMovieModel>

    @GET("3/movie/top_rated")
    fun getTopRated(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): Call<MainMovieModel>

    @GET("3/movie/now_playing")
    fun getTopToday(

        @Query("api_key") key: String,
        @Query("page") page: String
    ): Call<MainMovieModel>

    @GET("3/movie/upcoming")
    fun getUpcoming(

        @Query("api_key") key: String,
        @Query("page") page: String
    ): Call<MainMovieModel>


    @GET("3/movie/{id}")
    fun getMoviesByID(
        @Path("id") id: Int,
        @Query("api_key") key: String
    ): Call<MovieSearchResultModelByID>


    @GET("3/movie/{movie_id}/credits")
    fun getCastByID(

        @Path("movie_id") movieid:Int,
        @Query("api_key") key: String
    ): Call<MovieCastResponse>

    @GET("3/search/movie")
    fun getSearchedMoviesByName(
        @Query("api_key") key : String,
        @Query("query") query : String
    ) : Call<ByNameSearchResultModel>

    @GET("3/movie/{id}/videos")
    fun getMovieTrailerByID(
        @Path("id") id:Int,
        @Query("api_key") key: String

    ): Call<MovieTrailerModeByID>

    @GET("3/person/popular")
    fun getPopularActors(
        @Query("api_key") key : String,
        @Query("page") page : String
    ) : Call<ActorsResponse>
}