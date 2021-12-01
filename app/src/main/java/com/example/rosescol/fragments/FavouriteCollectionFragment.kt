package com.example.rosescol.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rosescol.MainActivity
import com.example.rosescol.PlantRepository.Singleton.listOfPlants
import com.example.rosescol.R
import com.example.rosescol.adapter.PlantAdapter
import com.example.rosescol.adapter.PlantItemDecoration

class FavouriteCollectionFragment (
    private val context : MainActivity
        ) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_favourite_collection,container,false)

        val list = ArrayList(listOfPlants.filter { it.likedOrNot })

        val collectionRecycler = view.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecycler.adapter = PlantAdapter(context,list,R.layout.item_vertical_plant)
        collectionRecycler.layoutManager = LinearLayoutManager(context)
        //collectionRecycler.addItemDecoration(PlantItemDecoration())

        return view
    }
}