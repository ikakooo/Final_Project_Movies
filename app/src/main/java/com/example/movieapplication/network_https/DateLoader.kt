package com.example.movieapplication.network_https


import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object DateLoader {


    //    private const val URL_API = "https://api.themoviedb.org/"
//    //3/movie/550?api_key=22c64b1eb2954257036c84ed667c4109
//    const val MOVIE_API_AUTOCOMPLETE = "3/movie/550?"
//
//    private val retrofit =
//        Retrofit.Builder()
//            .baseUrl(URL_API)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//    private val service = retrofit.create(
//        APIService::class.java)
//    private const val URL_API = "https://api.themoviedb.org/"
//    //3/movie/550?api_key=22c64b1eb2954257036c84ed667c4109
//    const val MOVIE_API_AUTOCOMPLETE = "3/movie/550?"
//    const val API_AUTOCOMPLETE = "3/movie/popular"
//    val api_key:String="0e03d86efe00ea1a1e1dd7d2a4717ba1"
//    var maxLimit : Int =996


    val retrofit= Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service=retrofit.create(APIService::class.java)

    fun getRequestTopToday(
        key: String,
        page: String,
        callback: FutureCallbackCountryBridge
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
        callback: FutureCallbackCountryBridge
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



    interface APIService {
        @GET("3/movie/popular")
        fun getPopular(
            @Query("api_key") key : String,
            @Query("page") page : String
        ) : Call<MainMovieModel>

        @GET("3/movie/top_rated")
        fun getTopToday(
            @Query("api_key") key:String,
            @Query("page") page : String
        ) : Call<MainMovieModel>
    }

}