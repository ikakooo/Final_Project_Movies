package com.example.movieapplication.detailed_actors_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.actors.model.ActorsResponseModel
import com.example.movieapplication.bottom_navigation.home.HomeFragment
import com.example.movieapplication.constants.Constants
import com.example.movieapplication.network_https.DataLoader
import com.example.movieapplication.network_https.futurecallbacks.FutureCallbackActorDetailsByIDBridge
import kotlinx.android.synthetic.main.activity_detailed_actors.*

class DetailedActorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_actors)
        supportActionBar?.hide()
        getActorDetails()

    }

    private fun getActorDetails() {
        val id = intent.getStringExtra("id")!!.toInt()
        DataLoader.getActorDetails(
            id,
            HomeFragment.API_KEY,
            object : FutureCallbackActorDetailsByIDBridge {
                override fun onResponseActorDetail(responseModel: ActorsResponseModel.Actor) {
                    d("jjakjakjka", responseModel.toString())
                    Glide.with(applicationContext)
                        .load(Constants.BASE_IMG_URL + responseModel.profile_path.toString())
                        .into(actorImage)
                    nameTV.text = responseModel.name.toString()
                    val replaced = responseModel.birthday.toString().replace("-", "/")
                    birthdayTextView.text = replaced
                    actorPopularity.text = responseModel.popularity.toString()
                    biographyTextView.text = responseModel.biography.toString()

                }


                override fun onFailure(error: String) {
                    d("errorString", error)
                }
            })
    }


}