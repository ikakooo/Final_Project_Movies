package com.example.movieapplication.network_https


import android.util.Log
import com.example.movieapplication.bottom_navigation.ui.actors.ActorsResponse
import com.example.movieapplication.bottom_navigation.ui.search.models.ByNameSearchResultModel
import com.example.movieapplication.detailed_movie_view.model.MovieCastResponse
import com.example.movieapplication.bottom_navigation.ui.home.models.MainMovieModel
import com.example.movieapplication.detailed_movie_view.model.MovieSearchResultModelByID
import com.example.movieapplication.detailed_movie_view.model.MovieTrailerModeByID
import com.example.movieapplication.network_https.futurecallbacks.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object DateLoader {
    private const val baseURLMovies = "https://api.themoviedb.org/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseURLMovies)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(APIService::class.java)





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

        callbackByID: FutureCallbackMoviesSearchByIDBridge
    ) {
        val call = service.getMoviesByID(id, key)
        call.enqueue(object : Callback<MovieSearchResultModelByID> {
            override fun onFailure(call: Call<MovieSearchResultModelByID>, t: Throwable) {
                callbackByID.onFailure(t.message.toString())
            }

            override fun onResponse(
                call: Call<MovieSearchResultModelByID>,
                response: Response<MovieSearchResultModelByID>
            ) {
                // //response.body()?.let { callback.onResponseSearchedByID(it) }

                response.body()?.let { callbackByID.onResponseSearchedByID(it) }
                Log.d("topRatedfbdfdava", response.body().toString())
            }
        })
    }

    fun getRequestedCastByID(
        id: Int,
        key: String,
        callback: FutureCallbackCastBridge
    ) {
        val call = service.getCastByID(id, key)
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


    fun getRequestSearchedMoviesByName(
        key: String,
        movieNameString: String,
        callback: FutureCallbackMoviesSearchByNameBridge
    ) {
        val call = service.getSearchedMoviesByName(key, movieNameString)
        call.enqueue(object : Callback<ByNameSearchResultModel> {
            override fun onFailure(call: Call<ByNameSearchResultModel>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }

            override fun onResponse(
                call: Call<ByNameSearchResultModel>,
                response: Response<ByNameSearchResultModel>
            ) {
                response.body()?.let { callback.onResponseSearchedByName(it) }
                Log.d("topRated", response.body().toString())
            }
        })
    }

    fun getPopularActors(
        page: String,
        key: String,
        callbackFutureCallback: FutureCallbackActorsCallbackBridge
    ) {
        val call = service.getPopularActors(key, page)
        call.enqueue(object : Callback<ActorsResponse> {

            override fun onFailure(call: Call<ActorsResponse>, t: Throwable) {
                callbackFutureCallback.onFailure(t.message.toString())
            }

            override fun onResponse(
                call: Call<ActorsResponse>,
                response: Response<ActorsResponse>
            ) {
                response.body()?.let { callbackFutureCallback.onResponseActor(it) }
            }

        })
    }


    fun getRequestedMovieTrailerByID(
        id: Int,
        key: String,
        callback: FutureCallbackMovieTrailerByIDBridge
    ) {
        val call = service.getMovieTrailerByID(id, key)
        call.enqueue(object : Callback<MovieTrailerModeByID> {
            override fun onFailure(call: Call<MovieTrailerModeByID>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }

            override fun onResponse(
                call: Call<MovieTrailerModeByID>,
                response: Response<MovieTrailerModeByID>
            ) {
                response.body()?.let { callback.onResponseMovieTrailerByID(it) }
                Log.d("topRsffeatedfbdfdava", response.body().toString())
            }
        })
    }

}