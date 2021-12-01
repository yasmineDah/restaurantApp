package com.example.rosescol.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.rosescol.MainActivity
import com.example.rosescol.Order
import com.example.rosescol.PlantRepository.Singleton.listOfOrders
import com.example.rosescol.R
import com.example.rosescol.adapter.OrderAdapter
import com.example.rosescol.adapter.PlantItemDecoration

class OrdersFragment (val context : MainActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_orders,container,false)


        val ordersRecyclerView = view.findViewById<RecyclerView>(R.id.orders_recycler_list)
        ordersRecyclerView.adapter = OrderAdapter(context, listOfOrders)
        ordersRecyclerView.addItemDecoration(PlantItemDecoration())

        return view
    }
}