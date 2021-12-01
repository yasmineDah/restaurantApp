package com.example.rosescol.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.rosescol.MainActivity
import com.example.rosescol.PlantModel
import com.example.rosescol.PlantRepository
import com.example.rosescol.PlantRepository.Singleton.listOfPlants
import com.example.rosescol.R

class MenuMeals (private val context : MainActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.meals_fragment,container,false)

        chooseMeal(view)

        return view
    }

    private fun chooseMeal(view : View) {
        /*val sandwishes = view.findViewById<TextView>(R.id.sandwiches_txtvw)
        val pizza = view.findViewById<TextView>(R.id.pizza_txtvw)
        val dishes = view.findViewById<TextView>(R.id.dishes_txtvw)
        val salads = view.findViewById<TextView>(R.id.salads_txtvw)*/
        val sandwishes = view.findViewById<ImageView>(R.id.sandwiches_image)
        val pizza = view.findViewById<ImageView>(R.id.pizza_image)
        val dishes = view.findViewById<ImageView>(R.id.dishes_image)
        val salads = view.findViewById<ImageView>(R.id.salads_image)

        val saladslist = ArrayList(listOfPlants.filter { it.type == "salads" })
        val sandwicheslist = ArrayList(listOfPlants.filter { it.type == "sandwiches" })
        val disheslist = ArrayList(listOfPlants.filter { it.type == "dishes" })
        val pizzalist = ArrayList(listOfPlants.filter { it.type == "pizza" })

        sandwishes.setOnClickListener { context.loadSelectedFragment(HomeFragment(context, sandwicheslist ), R.string.sandwich_meal)}
        salads.setOnClickListener { context.loadSelectedFragment(HomeFragment(context, saladslist), R.string.salad_meal) }
        dishes.setOnClickListener { context.loadSelectedFragment(HomeFragment(context, disheslist), R.string.dishes_meal) }
        pizza.setOnClickListener { context.loadSelectedFragment(HomeFragment(context, pizzalist), R.string.pizza_meal) }
    }
}