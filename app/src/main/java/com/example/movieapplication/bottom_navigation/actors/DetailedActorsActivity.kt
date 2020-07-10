package com.example.movieapplication.bottom_navigation.actors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.constants.Constants
import kotlinx.android.synthetic.main.activity_detailed_actors.*
import kotlinx.android.synthetic.main.activity_detailed_movie.*

class DetailedActorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_actors)
        supportActionBar?.hide()
        getActorDetails()
    }
    private fun getActorDetails(){
        val name = intent.getStringExtra("name")
        val id = intent.getIntExtra("id", 0).toString()
        d("kjakkajkjakjkajkajka", id)
        val path = intent.getStringExtra("path")
        val birthday = intent.getStringExtra("birthday")
        val biography = intent.getStringExtra("biography")

        d("jajahjhajh", biography.toString())
        birthdayTextView.text = birthday
        nameTV.text = name
        Glide.with(applicationContext).load(Constants.IMG_URL + path).into(actorImage)

    }
}