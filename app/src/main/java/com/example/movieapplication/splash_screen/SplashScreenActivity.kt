@file:Suppress("DEPRECATION")

package com.example.movieapplication.splash_screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.MovieNavigationActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        animate()

    }

    private var runnable = Runnable {
        setLoginActivity()
    }
    private var handler = Handler()


    override fun onStart() {
        super.onStart()
        handler.postDelayed(runnable, 4000)
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

    private fun animate(){
        val rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)
        imageView.animation = rotate
    }
}