package com.example.movieapplication.bottom_navigation.actors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.home.HomeFragment
import com.example.movieapplication.constants.Constants
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.futurecallbacks.ActorDetailsCallback
import kotlinx.android.synthetic.main.activity_detailed_actors.*
import kotlinx.android.synthetic.main.activity_detailed_movie.*
import kotlinx.android.synthetic.main.items_layout.view.*

class DetailedActorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_actors)
        supportActionBar?.hide()
        getActorDetails()

    }
    private fun getActorDetails(){
        val id = intent.getStringExtra("id")!!.toInt()
        DateLoader.getActorDetails(id, HomeFragment.API_KEY, object : ActorDetailsCallback{
            override fun onResponseActorDetail(response: ActorsResponse.Actor) {
                d("jjakjakjka", response.toString())
                Glide.with(applicationContext).load(Constants.IMG_URL + response.profile_path).into(actorImage)
                nameTV.text = response.name
                ///val replaced = response.birthday.replace("-", "/")
                birthdayTextView.text = response.birthday.toString()
                actorPopularity.text = response.popularity.toString()
                biographyTextView.text = response.biography
            }

            override fun onFailure(error: String) {
               d("errorString", error)

            }

        })
    }
}