package com.example.movieapplication.detailed_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapplication.R
import kotlinx.android.synthetic.main.activity_detailed_movie.*

class DetailedMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_movie)
        init()
    }
    private fun init(){
        val originalTitle = intent.getStringExtra("name")
        titleTV.text = originalTitle
    }
}