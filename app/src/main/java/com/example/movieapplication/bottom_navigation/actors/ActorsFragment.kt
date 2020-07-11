package com.example.movieapplication.bottom_navigation.actors

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import kotlinx.android.synthetic.main.fragment_actors.*
import kotlinx.android.synthetic.main.fragment_actors.view.*

class ActorsFragment : Fragment() {
    private var actorsList = mutableListOf<ActorsResponse.Actor>()
    private lateinit var actorsAdapter: ActorsAdapter

    private lateinit var actorsViewModel: ActorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        actorsViewModel = ViewModelProvider(this).get(ActorsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_actors, container, false)
        init(root)


        actorsViewModel.popularActorsLiveData.observe(viewLifecycleOwner, Observer{
            actorsList.addAll(it)
            actorsAdapter.notifyDataSetChanged()
        })
        return root
    }

    private fun init(root:View){

        root.moreActors.setOnClickListener {
            val intent = Intent(context, MoreActorsActivity::class.java)
            startActivity(intent)
        }
        actorsAdapter = ActorsAdapter(actorsList, object: DetailedMovieListener {
            override fun detailedViewClick(position: Int) {
                val actor = actorsList[position]
                val intent = Intent(context, DetailedActorsActivity::class.java)
                intent.putExtra("id", actor.id)
                startActivity(intent)
            }
        })

        root.actorsRecyclerView.layoutManager = GridLayoutManager(context, 2)
        root.actorsRecyclerView.isNestedScrollingEnabled = true
        root.actorsRecyclerView.setHasFixedSize(false)
        root.actorsRecyclerView.adapter = actorsAdapter
    }
}