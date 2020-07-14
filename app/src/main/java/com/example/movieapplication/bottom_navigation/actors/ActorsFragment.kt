package com.example.movieapplication.bottom_navigation.actors

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.bottom_navigation.actors.adapter.ActorsAdapter
import com.example.movieapplication.bottom_navigation.actors.model.ActorsResponseModel
import com.example.movieapplication.bottom_navigation.actors.more_actors_activity.MoreActorsActivity
import com.example.movieapplication.detailed_actors_view.DetailedActorsActivity
import com.example.movieapplication.detailed_movie_view.DetailedMovieListener
import kotlinx.android.synthetic.main.fragment_actors.view.*

class ActorsFragment : Fragment() {
    private var actorsList = mutableListOf<ActorsResponseModel.Actor>()
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


        actorsViewModel.popularActorsLiveDataModel.observe(viewLifecycleOwner, Observer{
            actorsList.addAll(it)
            root.progressBarVisibilityID.isVisible = false
            actorsAdapter.notifyDataSetChanged()
        })
        return root
    }

    private fun init(root:View){

        root.moreActors.setOnClickListener {
            val intent = Intent(context, MoreActorsActivity::class.java)
            startActivity(intent)
        }
        actorsAdapter =
            ActorsAdapter(
                actorsList,
                object : DetailedMovieListener {
                    override fun detailedViewClick(position: Int) {
                        val actor = actorsList[position]
                        val intent = Intent(context, DetailedActorsActivity::class.java)
                        intent.putExtra("id", actor.id)
//                        intent.putExtra("putExtraName", actor.name)
//                        intent.putExtra("putExtraProfile_path", actor.profile_path)
//                        intent.putExtra("putExtraPopularity", actor.popularity)
//                        intent.putExtra("putExtraBirthday", actor.birthday)
//                        intent.putExtra("putExtraBiography", actor.biography)
                        startActivity(intent)
                    }
                })

        root.actorsRecyclerView.layoutManager = GridLayoutManager(context, 2)
        root.actorsRecyclerView.isNestedScrollingEnabled = true
        root.actorsRecyclerView.setHasFixedSize(false)
        root.actorsRecyclerView.adapter = actorsAdapter
    }
}