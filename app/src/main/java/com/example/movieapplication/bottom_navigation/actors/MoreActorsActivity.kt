package com.example.movieapplication.bottom_navigation.actors

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.constants.Constants
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.futurecallbacks.FutureCallbackActorsBridge
import com.example.movieapplication.network_https.futurecallbacks.FutureCallbackMoviesBridge
import kotlinx.android.synthetic.main.activity_more_actors.*

class MoreActorsActivity : AppCompatActivity() {
    private var actorsList = mutableListOf<ActorsResponse.Actor>()
    private lateinit var actorsAdapter: ActorsAdapter
    private var pageCounter = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_actors)
        supportActionBar!!.hide()
        init()
    }

    private fun init() {
        actorsAdapter = ActorsAdapter(actorsList, object : DetailedMovieListener {
            override fun detailedViewClick(position: Int) {
                val actor = actorsList[position]
                intent.putExtra("id", actor.id)
                val intent = Intent(this@MoreActorsActivity, DetailedActorsActivity::class.java)
                startActivity(intent)
            }

        })

        moreActorsRecyclerview.layoutManager = GridLayoutManager(this, 2)
        moreActorsRecyclerview.adapter = actorsAdapter
        getAllActors()
        pageCounter++

        moreActorsRecyclerview.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    getAllActors()
                    pageCounter++

                }
            }
        })

    }

    private fun getAllActors() {
        DateLoader.getPopularActors(pageCounter.toString(), Constants.API_KEY, object : FutureCallbackActorsBridge{
            override fun onResponseActor(response: ActorsResponse) {
                d("responsee", response.toString())
                actorsList.addAll( response.results)
                actorsAdapter.notifyDataSetChanged()
            }

            override fun onFailure(error: String) {

            }

        })
    }


}