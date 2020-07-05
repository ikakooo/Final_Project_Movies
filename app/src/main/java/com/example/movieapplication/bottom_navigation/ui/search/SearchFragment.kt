package com.example.movieapplication.bottom_navigation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.ui.search.models.SearchResultModelResultList
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {
    private var searchResultByNameMoviesList = mutableListOf<SearchResultModelResultList>()
    private lateinit var  searchResultByNameAdapter: SearchResultRecyclerViewAdapter

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        init(root)




        searchViewModel.searchResultByNameMoviesLiveData.observe(viewLifecycleOwner, Observer {

            searchResultByNameMoviesList.addAll(it)
            searchResultByNameAdapter.notifyDataSetChanged()

        })

        return root
    }



    private fun init(root: View){
        searchResultByNameAdapter = SearchResultRecyclerViewAdapter(searchResultByNameMoviesList)
        root.searchResultRecyclerViewID.layoutManager = GridLayoutManager(context, 2)
        root.searchResultRecyclerViewID.adapter = searchResultByNameAdapter

    }





}