package com.example.rosescol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rosescol.PlantRepository.Singleton.listOfPlants
import com.example.rosescol.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadSelectedFragment(MenuMeals(this), R.string.meals_page)


        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)

        navigationView.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home_page ->{
                    loadSelectedFragment(MenuMeals(this), R.string.home_page_title)
                    true
                }

                R.id.favourite_collection_page ->{
                    loadSelectedFragment(FavouriteCollectionFragment(this), R.string.collection_page_title)
                    true
                }
                R.id.add_plant_page ->{
                    loadSelectedFragment(AddFragment(this), R.string.add_plant_page_title)
                    true
                }
                R.id.order ->{
                    loadSelectedFragment(OrdersFragment(this),R.string.orders_page)
                }
                else -> false
            }
        }
    }

     fun loadSelectedFragment(fragment: Fragment, pageTitle : Int) {
        val repository = PlantRepository()

        findViewById<TextView>(R.id.page_title).text = resources.getString(pageTitle)

        repository.updateData{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}