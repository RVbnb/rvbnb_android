package com.example.rvbnb.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rvbnb.R
import com.example.rvbnb.adapter.PlacesAdapter
import com.example.rvbnb.model.Land
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details_rvowner.*
import java.io.InputStream

class DetailsActivity : AppCompatActivity() {

    var photoUri: Uri? = null

    companion object {
        internal const val REQUEST_IMAGE_GET = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        title = "Land Details"

        val displayLand: Land = intent.getSerializableExtra(PlacesAdapter.LAND_KEY) as Land

        et_listing_address_details.setText(displayLand.location)
        //et_listing_availability_details.setText(displayLand.)
        et_listing_description_details.setText(displayLand.description)
        et_listing_price_details.setText(displayLand.price_per_day.toString())

//        var urlString = "https://scx1.b-cdn.net/csz/news/800/2018/3-ocean.jpg"

        try {
            Picasso.get().load(displayLand.photo).into(iv_listing_details)
        }catch (e: Exception){
            Log.i("ImageLinkBad", "True", e)
        }


        // When user clicks on photo, user can select another photo to update with.
        iv_listing_details.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent,
                    REQUEST_IMAGE_GET
                )
            }
        }

        // When users clicks on Cancel, user will be brought back to the Homepage.
        btn_listing_cancel_details.setOnClickListener {
            val cancelIntent = Intent(this, LandownerActivity::class.java)
            startActivity(cancelIntent)
        }

        btn_listing_update_details.setOnClickListener {
            // TODO: add code for all of the parts that could be updated
            val updateIntent = Intent(this, LandownerActivity::class.java)
            startActivity(updateIntent)
        }

        btn_listing_delete_details.setOnClickListener {
            // TODO: .removeAt(id)
            val deleteIntent = Intent(this, LandownerActivity::class.java)
            startActivity(deleteIntent)
        }
    }

    // The photo that was selected by the user will be set.
    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            val uri = data!!.data
            photoUri = uri
            iv_listing_details.setImageURI(uri)
        }
    }
}
