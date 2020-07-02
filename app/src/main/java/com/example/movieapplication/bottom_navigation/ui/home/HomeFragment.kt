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
import com.example.movieapplication.Adapter
import com.example.movieapplication.R
import com.example.movieapplication.splash_screen.ModelItem
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private var ItemsList= mutableListOf<ModelItem>()
    private lateinit var adapter: Adapter

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
           ///irakli
        //adapter= Adapter(ItemsList)
        //topTodayRecyclerView.layoutManager= LinearLayoutManager(context)
        //topTodayRecyclerView.adapter=adapter


        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
            //ItemsList.add(ModelItem("jahjah"))
            //ItemsList.add(ModelItem("aaaaa"))

        })



        return root
    }
}