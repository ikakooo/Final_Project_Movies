package com.example.movieapplication.bottom_navigation.actors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapplication.R
import kotlinx.android.synthetic.main.activity_detailed_actors.*

class DetailedActorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_actors)
        getActorDetails()
    }
    private fun getActorDetails(){
        val name = intent.getStringExtra("name")
        val id = intent.getIntExtra("id", 0).toString()
        val path = intent.getStringExtra("path")
        val birthday = intent.getStringExtra("birthday")

        nameTV.text = name

    }
}