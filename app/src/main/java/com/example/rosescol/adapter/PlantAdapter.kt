package com.example.rosescol.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rosescol.*
import com.example.rosescol.fragments.HomeFragment

class PlantAdapter (
    val context : MainActivity,
    private val plantsList : ArrayList<PlantModel>,
    private  val layoutId : Int
                    ): RecyclerView.Adapter<PlantAdapter.ViewHolder>(){

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val plantImage = view.findViewById<ImageView>(R.id.image_item)
        val plantName : TextView? = view.findViewById(R.id.name_item)
        val plantDescription : TextView? = view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPlant = plantsList[position]

        val repo = PlantRepository()

        Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.plantImage)
        holder.plantName?.text = currentPlant.name
        holder.plantDescription?.text = currentPlant.description

        updateStar(currentPlant,holder)
        holder.starIcon.setOnClickListener {
            currentPlant.likedOrNot = !currentPlant.likedOrNot
            updateStar(currentPlant,holder)
            repo.updatePlant(currentPlant)
        }

        holder.itemView.setOnClickListener{
            PlantPopUp(this, currentPlant).show()
        }

    }
    fun updateStar(plant : PlantModel, view : ViewHolder){
        if(plant.likedOrNot)
            view.starIcon.setImageResource(R.drawable.ic_star)
        else
            view.starIcon.setImageResource(R.drawable.ic_unstar)
    }

    override fun getItemCount(): Int = plantsList.size
}
