package com.example.rosescol.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.rosescol.MainActivity
import com.example.rosescol.PlantModel
import com.example.rosescol.PlantRepository.Singleton.listOfPlants
import com.example.rosescol.R
import com.example.rosescol.adapter.PlantAdapter
import com.example.rosescol.adapter.PlantItemDecoration

class HomeFragment (private val context : MainActivity,
                    private val list : ArrayList<PlantModel>) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home,container,false)

        //val list = ArrayList(listOfPlants.filter { !it.likedOrNot })

        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = PlantAdapter(context ,list, R.layout.item_horizontal_plant)


        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = PlantAdapter(context ,list, R.layout.item_vertical_plant)
        //verticalRecyclerView.addItemDecoration(PlantItemDecoration()) // because the size of the view is variable


        return view
    }
}