package com.example.movieapplication.detailed_actors_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.actors.model.ActorsResponseModel
import com.example.movieapplication.bottom_navigation.home.HomeFragment
import com.example.movieapplication.constants.Constants
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.futurecallbacks.ActorDetailsCallback
import kotlinx.android.synthetic.main.activity_detailed_actors.*

class DetailedActorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_actors)
        supportActionBar?.hide()
        //getActorDetails()
        initView()

    }
    private fun getActorDetails(){
        val id = intent.getStringExtra("id")!!.toInt()
        DateLoader.getActorDetails(id, HomeFragment.API_KEY, object : ActorDetailsCallback{
            override fun onResponseActorDetail(responseModel: ActorsResponseModel.Actor) {
                d("jjakjakjka", responseModel.toString())
                Glide.with(applicationContext).load(Constants.BASE_IMG_URL + responseModel.profile_path).into(actorImage)
                nameTV.text = responseModel.name
                ///val replaced = response.birthday.replace("-", "/")
                birthdayTextView.text = responseModel.birthday.toString()
                actorPopularity.text = responseModel.popularity.toString()
                biographyTextView.text = responseModel.biography
            }

            override fun onFailure(error: String) {
               d("errorString", error)

            }

        })
    }

    private  fun initView(){
        val profilePath = intent.getStringExtra("putExtraID")
        val name = intent.getStringExtra("putExtraName")
        d("namenamename", name.toString())
        val popularity = intent.getStringExtra("putExtraPopularity")
        val birthday = intent.getStringExtra("putExtraBirthday")
        val biography = intent.getStringExtra("putExtraBiography")
        Glide.with(applicationContext).load(Constants.BASE_IMG_URL + profilePath.toString()).into(actorImage)
        nameTV.text = name.toString()
        ///val replaced = response.birthday.replace("-", "/")
        birthdayTextView.text = birthday.toString()
        actorPopularity.text = popularity.toString()
        biographyTextView.text = biography.toString()
    }


}