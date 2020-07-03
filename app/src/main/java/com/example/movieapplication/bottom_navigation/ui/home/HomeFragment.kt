package com.example.movieapplication.bottom_navigation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.adapters.PopularAdapter
import com.example.movieapplication.adapters.TopTodayAdapter
import com.example.movieapplication.splash_screen.ModelItem
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    private var ItemsList = mutableListOf<ModelItem>()
    private lateinit var topTodayAdapter: TopTodayAdapter
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        topTodayAdapter =
            TopTodayAdapter(ItemsList)
        root.topTodayRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        root.topTodayRecyclerView.adapter = topTodayAdapter

        popularAdapter = PopularAdapter(ItemsList)
        root.popularRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        root.popularRecyclerView.adapter = popularAdapter
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
            ItemsList.add(ModelItem("movie_1"))
            ItemsList.add(ModelItem("movie_2"))
            ItemsList.add(ModelItem("movie_3"))
            ItemsList.add(ModelItem("movie_4"))
            ItemsList.add(ModelItem("movie_5"))
            ItemsList.add(ModelItem("movie_6"))
        })
        return root
    }
}