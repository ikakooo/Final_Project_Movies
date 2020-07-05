package com.example.movieapplication.bottom_navigation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.bottom_navigation.ui.search.models.SearchResultModelResultList


class SearchViewModel : ViewModel() {

    private val _searchResultMoviesLiveData = MutableLiveData<MutableList<SearchResultModelResultList>>().apply {

    }
    val searchResultMoviesLiveData: LiveData<MutableList<SearchResultModelResultList>> = _searchResultMoviesLiveData

    ////MovieSearchResultModelByID
}