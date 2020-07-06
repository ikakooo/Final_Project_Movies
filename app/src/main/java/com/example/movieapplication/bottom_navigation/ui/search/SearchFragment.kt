package com.example.movieapplication.bottom_navigation.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.ui.home.HomeFragment
import com.example.movieapplication.bottom_navigation.ui.search.models.ByNameSearchResultModel
import com.example.movieapplication.bottom_navigation.ui.search.models.SearchResultModelResultList
import com.example.movieapplication.network_https.DateLoader
import com.example.movieapplication.network_https.FutureCallbackMoviesSearchByNameBridge
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {
    private var searchResultByNameMoviesList = mutableListOf<SearchResultModelResultList>()
    private lateinit var searchResultByNameAdapter: SearchResultRecyclerViewAdapter

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        init(root)

//        searchViewModel.searchResultByNameMoviesLiveData.observe(viewLifecycleOwner, Observer {
//
////            searchResultByNameMoviesList.addAll(it)
////            searchResultByNameAdapter.notifyDataSetChanged()
//
//        })

        return root
    }


    private fun init(root: View) {
        searchResultByNameAdapter = SearchResultRecyclerViewAdapter(searchResultByNameMoviesList)
        root.searchResultRecyclerViewID.layoutManager = GridLayoutManager(context, 2)
        root.searchResultRecyclerViewID.adapter = searchResultByNameAdapter
        val textWatcher = object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0.toString()
            }

        }

        root.moviesSearchEditTextID.addTextChangedListener(textWatcher)


    }


    private fun getPostsMoviesSearchByName(movieNameString: String) {


        DateLoader.getRequestSearchedMoviesByName(
            HomeFragment.API_KEY, movieNameString,
            object : FutureCallbackMoviesSearchByNameBridge {
                override fun onResponseSearchedByName(response: ByNameSearchResultModel) {
                    Log.d("sffdjsdsdfdfgfsdfs", response.toString())
                    (0 until (response.results?.size ?: 1)-1).forEach{ it->
                        searchResultByNameMoviesList.add(
                            SearchResultModelResultList(
                                response.results?.get(it)?.backdrop_path.toString(),
                                response.results?.get(it)?.id.toString().toInt(),
                                response.results?.get(it)?.original_language.toString(),
                                response.results?.get(it)?.original_title.toString(),
                                response.results?.get(it)?.overview.toString(),
                                response.results?.get(it)?.popularity.toString().toDouble(),
                                response.results?.get(it)?.poster_path.toString(),
                                response.results?.get(it)?.release_date.toString(),
                                response.results?.get(it)?.title.toString(),
                                response.results?.get(it)?.vote_average.toString().toDouble(),
                                response.results?.get(it)?.vote_count.toString().toInt()
                            ))}
                    Log.d("eewffwee", searchResultByNameMoviesList.toString())
                    searchResultByNameAdapter.notifyDataSetChanged()
                }

                override fun onFailure(error: String) { Log.d("dfgfgdfgdfg", error.toString())}
            })
    }


}