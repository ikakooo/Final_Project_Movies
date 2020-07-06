package com.example.movieapplication.detailed_view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.ui.home.HomeFragment
import com.example.movieapplication.detailed_view.model.MovieCastResponse
import com.example.movieapplication.detailed_view.model.MovieSearchResultModelByID
import com.example.movieapplication.detailed_view.model.MovieTrailerModeByID
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.FutureCallbackCastBridge
import com.example.movieapplication.network_https.FutureCallbackMovieTrailerByIDBridge
import com.example.movieapplication.network_https.FutureCallbackMoviesSearchByIDBridge
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_detailed_movie.*


class DetailedMovieActivity : AppCompatActivity() {
    private var castList = mutableListOf<MovieCastResponse.MovieCast>()
    lateinit var castAdapter: CastAdapter
    lateinit var youtubeVideoID: String
    val imgBaseURL = "https://image.tmdb.org/t/p/w780/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_movie)
        init()
    }
    private fun init(){
        var movieID = intent.getStringExtra("movieID")
        d("sdfdfdsf", movieID.toString())
        val originalTitle = intent.getStringExtra("name")
        titleTV.text = originalTitle
        if (movieID == null) { movieID="531454" }
        getPostsDetailedMovie(movieID.toInt())
        getPostsDetailedCast(movieID.toInt())
        getPostsDetailedTrailer(movieID.toInt())
        castAdapter = CastAdapter(castList)
        castRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        castRecyclerView.adapter = castAdapter

        //Player
        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = "ou7KSmfC3lA"
                d("fjsdfksdf",youtubeVideoID.toString())
                youTubePlayer.loadVideo(youtubeVideoID, 0f)
            }
        })



    }

    private fun getPostsDetailedMovie(id: Int) {
        DateLoader.getRequestedMovieByID(
            id, HomeFragment.API_KEY,
            object : FutureCallbackMoviesSearchByIDBridge {
                @SuppressLint("SetTextI18n")
                override fun onResponseSearchedByID(response: MovieSearchResultModelByID) {
                    d("fsfesefesfsf", response.toString())
                    titleDetailedTextViewID.text = response.overview
                    titleTV.text = response.original_title
                    ratingTV.text = response.vote_average+"/10"
                    (0 until response.genres.size).forEach{
                        val text = genreTVID.text.toString()
                        genreTVID.text = text + " " + response.genres[it].name
                    }
                    Glide.with(applicationContext).load(imgBaseURL + response.poster_path).into(moviesDetailedImageViewID)
                    Glide.with(applicationContext).load(imgBaseURL + response.backdrop_path).into(detailedBackground)
                }
                override fun onFailure(error: String) {
                    d("detailedResponse", error)
                }
            }
        )
    }

    private  fun getPostsDetailedCast(id: Int){
        DateLoader.getRequestedCastByID(id,HomeFragment.API_KEY,object : FutureCallbackCastBridge{
            override fun onResponseCastByID(response: MovieCastResponse) {
                  d("dfsdfsdf",response.toString())
                val size = response.cast?.size.toString().toInt()
                (0 until size).forEach{ it->
                    castList.add(MovieCastResponse.MovieCast(
                        response.cast?.get(it)?.cast_id.toString().toInt(),
                        response.cast?.get(it)?.name.toString(),
                        response.cast?.get(it)?.profile_path.toString(), response.cast?.get(it)?.id.toString()))

                }
                castAdapter.notifyDataSetChanged()
                d("jakja", castList.size.toString())
            }
            override fun onFailure(error: String) {
            }
        })
    }

    private  fun getPostsDetailedTrailer(id: Int){
        DateLoader.getRequestedMovieTrailerByID(id,HomeFragment.API_KEY,object : FutureCallbackMovieTrailerByIDBridge{
            override fun onResponseMovieTrailerByID(response: MovieTrailerModeByID) {
                d("dfsdfhghffsdf",response.toString())

                (0 until response.results.size).forEach{
                    youtubeVideoID = if (response.results[it].size==1080) response.results[it].key
                    else "hfhsd"
                }


            }

            override fun onFailure(error: String) {
            }
        })
    }
}