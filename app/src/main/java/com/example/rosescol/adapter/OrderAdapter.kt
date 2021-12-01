package com.example.rosescol.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rosescol.MainActivity
import com.example.rosescol.Order
import com.example.rosescol.PlantRepository
import com.example.rosescol.R
import com.example.rosescol.fragments.OrdersFragment

class OrderAdapter (val context : MainActivity,
                    private val ordersList : ArrayList<Order>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>(){

    class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
        val dishName = view.findViewById<TextView>(R.id.dish_name)
        val condiments = view.findViewById<TextView>(R.id.condimentstv)
        val tableNumber = view.findViewById<TextView>(R.id.table_numbertv)
        val deleteImg = view.findViewById<ImageView>(R.id.trash_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item,parent,false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentOrder = ordersList[position]

        holder.dishName.text = currentOrder.dishName
        var condiment  = ""
        if(currentOrder.harissaChecked)
            condiment += " Harissa"
        if(currentOrder.ketchupChecked)
            condiment += " Ketchup"
        if(currentOrder.mayonnaiseChecked)
            condiment += " Mayonnaise"
        holder.condiments.text = condiment
        holder.tableNumber.text = currentOrder.tableNumber

        holder.deleteImg.setOnClickListener {
            PlantRepository().deleteOrder(currentOrder)
            PlantRepository().updateData {
                context.loadSelectedFragment(OrdersFragment(context),R.string.orders_page)
            }
        }
    }

    override fun getItemCount() : Int = ordersList.size
}