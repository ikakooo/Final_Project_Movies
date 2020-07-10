package com.example.movieapplication.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.MovieNavigationActivity

class SplashScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
    }

    private var runnable= Runnable {
        setLoginActivity()
    }
    private var handler = Handler()


    override fun onStart() {
        super.onStart()
        handler.postDelayed(runnable, 5000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)

    }




    private fun setLoginActivity() {

        val intent = Intent(this, MovieNavigationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }
}