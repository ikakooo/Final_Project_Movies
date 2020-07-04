package com.example.movieapplication.bottom_navigation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.ui.home.adapters.TopTodayAdapter
import com.example.movieapplication.bottom_navigation.ui.home.adapters.UpcomingAdapter
import com.example.movieapplication.network_https.models.MovieSearchResultModelByID
import com.example.movieapplication.network_https.models.movie
import kotlinx.android.synthetic.main.fragment_home.view.*

class SearchFragment : Fragment() {
    private var searchResultMoviesList = mutableListOf<MovieSearchResultModelByID>()
    private lateinit var  searchResultAdapter: SearchResultRecyclerViewAdapter

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        init(root)




        searchViewModel.searchResultMoviesLiveData.observe(viewLifecycleOwner, Observer {

            searchResultMoviesList.addAll(it)
            searchResultAdapter.notifyDataSetChanged()

        })

        return root
    }



    private fun init(root: View){
        searchResultAdapter = SearchResultRecyclerViewAdapter(searchResultMoviesList)
        root.topTodayRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        root.topTodayRecyclerView.adapter = searchResultAdapter


    }
}