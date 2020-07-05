package com.example.movieapplication.bottom_navigation.ui.search

import android.util.Log.d
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.bottom_navigation.ui.home.HomeFragment
import com.example.movieapplication.bottom_navigation.ui.search.models.ByNameSearchResultModel
import com.example.movieapplication.bottom_navigation.ui.search.models.SearchResultModelResultList
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.FutureCallbackMoviesSearchByNameBridge


class SearchViewModel : ViewModel() {

    private val _searchResultByNameMoviesLiveData = MutableLiveData<MutableList<SearchResultModelResultList>>().apply {
        getPostsMoviesSearchByName("room")


    }
    val searchResultByNameMoviesLiveData: LiveData<MutableList<SearchResultModelResultList>> = _searchResultByNameMoviesLiveData




    private fun getPostsMoviesSearchByName(movieNameString: String) {


        DateLoader.getRequestSearchedMoviesByName(
            HomeFragment.API_KEY, movieNameString,
            object : FutureCallbackMoviesSearchByNameBridge {
                override fun onResponseSearchedByName(response: ByNameSearchResultModel) {
                    //_upComingMoviesLiveData.value = response.results.toMutableList()
                    d("sffdjsdfsdfs",response.toString())
                    _searchResultByNameMoviesLiveData.value = response.results.toMutableList()

                }

                override fun onFailure(error: String) {}
            })
    }
}