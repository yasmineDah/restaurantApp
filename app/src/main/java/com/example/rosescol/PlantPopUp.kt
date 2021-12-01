package com.example.rosescol

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rosescol.adapter.PlantAdapter
import java.util.*

class PlantPopUp (private val adapter : PlantAdapter,
                  private val currentPlant : PlantModel
): Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_plants_details)

        setUpComponents()

        setupCloseButton()

        setUpDeleteButton()

        setUpOrderButton()
    }

    private fun setUpOrderButton() {
        val orderIcon = findViewById<ImageView>(R.id.order_button)
        orderIcon.setOnClickListener {
            orderIcon.setImageResource(R.drawable.ic_order_done)
            val harissaWanted = findViewById<CheckBox>(R.id.harissachecked).isChecked
            val ketchupWanted = findViewById<CheckBox>(R.id.ketchupchecked).isChecked
            val mayonnaiseWanted = findViewById<CheckBox>(R.id.mayonnaisechecked).isChecked
            val table = findViewById<Spinner>(R.id.spinner).selectedItem.toString()
            val order = Order(UUID.randomUUID().toString(),currentPlant.name,harissaWanted,ketchupWanted,mayonnaiseWanted,table)
            PlantRepository().insertOrder(order)
        }
    }

    private fun setUpDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener {
            PlantRepository().deletePlant(currentPlant)
            dismiss()
        }
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener {
            dismiss()
        }
    }

    private fun setUpComponents() {
        val plantImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentPlant.imageUrl)).into(plantImage)
        findViewById<TextView>(R.id.popup_plant_name).text = currentPlant.name
        findViewById<TextView>(R.id.popup_plant_description_subtitle).text = currentPlant.description
    }
}