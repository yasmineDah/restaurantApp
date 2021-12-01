package com.example.rosescol

import android.net.Uri
import com.example.rosescol.PlantRepository.Singleton.databaseRef
import com.example.rosescol.PlantRepository.Singleton.databaseRefOrders
import com.example.rosescol.PlantRepository.Singleton.downloadUri
import com.example.rosescol.PlantRepository.Singleton.listOfOrders
import com.example.rosescol.PlantRepository.Singleton.listOfPlants
import com.example.rosescol.PlantRepository.Singleton.storageReference
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.util.*

class PlantRepository {
    object Singleton {
        val databaseRef = FirebaseDatabase.getInstance().getReference("plants")
        val listOfPlants = arrayListOf<PlantModel>()

        val databaseRefOrders = FirebaseDatabase.getInstance().getReference("orders")
        val listOfOrders = arrayListOf<Order>()

        var downloadUri : Uri? = null

        private val BUCKET_URL: String = "gs://rosescol-1a34a.appspot.com"
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)
    }

    fun updateData (callback : () -> Unit){

        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                listOfPlants.clear()
                for (ds in snapshot.children){
                    val plant = ds.getValue(PlantModel::class.java) // so the instance retrieved will have the same format as PlantModel
                    if(plant != null)
                        listOfPlants.add(plant)
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        //*********************************************************************
        databaseRefOrders.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listOfOrders.clear()
                for (ds in snapshot.children){
                    val order = ds.getValue(Order::class.java)
                    if(order != null)
                        listOfOrders.add(order)

                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
        callback()
    }


    fun stockImage(file : Uri, callback: () -> Unit){
        if(file != null){
            val filename = UUID.randomUUID().toString() + ".jpg"
            val ref = storageReference.child(filename)
            val uploadTask = ref.putFile(file)


            uploadTask.continueWithTask(Continuation <UploadTask.TaskSnapshot,Task<Uri>>{ task ->

                if(!task.isSuccessful)
                    task.exception?.let {throw  it}

                return@Continuation ref.downloadUrl

            }).addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    downloadUri = task.result
                    callback()
                }
            }
        }
    }

    fun updatePlant (plant : PlantModel) = databaseRef.child(plant.id).setValue(plant)

    fun insertPlant (plant : PlantModel) = databaseRef.child(plant.id).setValue(plant)

    fun insertOrder(order : Order ) = databaseRefOrders.child(order.id).setValue(order)

    fun deletePlant(plant : PlantModel) = databaseRef.child(plant.id).removeValue()

    fun deleteOrder (order : Order) = databaseRefOrders.child(order.id).removeValue()

}