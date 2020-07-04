package com.example.movieapplication.network_https


import android.util.Log
import com.example.movieapplication.detailed_view.model.MovieCastResponse
import com.example.movieapplication.network_https.models.MainMovieModel
import com.example.movieapplication.network_https.models.MovieSearchResultModelByID
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object DateLoader {

    const val api_key: String = "0e03d86efe00ea1a1e1dd7d2a4717ba1"
    var maxLimit: Int = 996
    val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(APIService::class.java)

    fun getRequestTopToday(
        key: String,
        page: String,
        callback: FutureCallbackMoviesBridge
    ) {
        val call = service.getTopToday(key, page)
        call.enqueue(object : Callback<MainMovieModel> {
            override fun onFailure(call: Call<MainMovieModel>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }

            override fun onResponse(
                call: Call<MainMovieModel>,
                response: Response<MainMovieModel>
            ) {
                response.body()?.let { callback.onResponse(it) }
                Log.d("topToday", response.body().toString())
            }

        })
    }


    fun getRequestPopular(
        key: String,
        page: String,
        callback: FutureCallbackMoviesBridge
    ) {
        val call = service.getPopular(key, page)
        call.enqueue(object : Callback<MainMovieModel> {
            override fun onFailure(call: Call<MainMovieModel>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }

            override fun onResponse(
                call: Call<MainMovieModel>,
                response: Response<MainMovieModel>
            ) {
                response.body()?.let { callback.onResponse(it) }
                Log.d("Popular", response.body().toString())
            }

        })
    }


    fun getRequestTopRated(
        key: String,
        page: String,
        callback: FutureCallbackMoviesBridge
    ) {
        val call = service.getTopRated(key, page)
        call.enqueue(object : Callback<MainMovieModel> {
            override fun onFailure(call: Call<MainMovieModel>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }

            override fun onResponse(
                call: Call<MainMovieModel>,
                response: Response<MainMovieModel>
            ) {
                response.body()?.let { callback.onResponse(it) }
                Log.d("topRated", response.body().toString())
            }

        })
    }

    fun getRequestUpComing(
        key: String,
        page: String,
        callback: FutureCallbackMoviesBridge
    ) {
        val call = service.getUpcoming(key, page)
        call.enqueue(object : Callback<MainMovieModel> {
            override fun onFailure(call: Call<MainMovieModel>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }

            override fun onResponse(
                call: Call<MainMovieModel>,
                response: Response<MainMovieModel>
            ) {
                response.body()?.let { callback.onResponse(it) }
                Log.d("topRated", response.body().toString())
            }
        })
    }

    fun getRequestedMovieByID(
        id: Int,
        key: String,

        callback: FutureCallbackMoviesSearchBridge
    ) {
        val call = service.getMoviesByID(id,key)
        call.enqueue(object : Callback<MovieSearchResultModelByID> {
            override fun onFailure(call: Call<MovieSearchResultModelByID>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }

            override fun onResponse(
                call: Call<MovieSearchResultModelByID>,
                response: Response<MovieSearchResultModelByID>
            ) {
               // //response.body()?.let { callback.onResponseSearchedByID(it) }

                response.body()?.let { callback.onResponseSearchedByID(it) }
                Log.d("topRatedfbdfdava", response.body().toString())
            }
        })
    }

    fun getRequestedCastByID(
        id: Int,
        key: String,
        callback: FutureCallbackCastBridge
    ) {
        val call = service.getCastByID(id,key)
        call.enqueue(object : Callback<MovieCastResponse> {
            override fun onFailure(call: Call<MovieCastResponse>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }

            override fun onResponse(
                call: Call<MovieCastResponse>,
                response: Response<MovieCastResponse>
            ) {
                response.body()?.let { callback.onResponseCastByID(it) }
                Log.d("topRsffeatedfbdfdava", response.body().toString())
            }
        })
    }



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
        ):Call<MovieCastResponse>
    }

}