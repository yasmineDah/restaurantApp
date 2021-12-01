package com.example.rosescol.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.rosescol.MainActivity
import com.example.rosescol.PlantModel
import com.example.rosescol.PlantRepository
import com.example.rosescol.PlantRepository.Singleton.downloadUri
import com.example.rosescol.R
import java.util.*

class AddFragment (private val context : MainActivity): Fragment() {

    private var uploadedImage : ImageView? = null
    private var file : Uri? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_add,container,false)

        uploadedImage =  view.findViewById(R.id.preview_image)

        val pickupImageBtn = view.findViewById<Button>(R.id.upload_button)

        pickupImageBtn.setOnClickListener { pickUpIamge() }

        val confirmButton = view.findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener { sendForm(view) }

        return view
    }

    private fun sendForm(view : View) {
        val repo = PlantRepository()
        repo.stockImage(file!!){
            val plantName = view.findViewById<EditText>(R.id.name_input).text.toString()
            val plantDescription = view.findViewById<EditText>(R.id.description_input).text.toString()
            val type = view.findViewById<Spinner>(R.id.type_spinner).selectedItem.toString()
            val downloadImageUri = downloadUri

            val plant = PlantModel(
                UUID.randomUUID().toString(),
                plantName,
                type,
                plantDescription,
                downloadImageUri.toString()
            )
            repo.insertPlant(plant)
        }
    }

    private fun pickUpIamge() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        // l'instruction suivante est pour déclencher l'action et d'attendre l'image récupérée
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),40)
        // the requestCode specify the action to be done 
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // this method is called when the image is ready to be received
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 40 && resultCode == Activity.RESULT_OK){
            // cette condition "resultCode == Activity.RESULT_OK" nous assure que l'utilisateur a bien choisi une image

            if(data == null || data.data == null) return

            file = data.data

            uploadedImage?.setImageURI(file)

        }
    }
}