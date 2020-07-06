package com.example.movieapplication.bottom_navigation.ui.actors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActorsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Actors"
    }
    val text: LiveData<String> = _text
}