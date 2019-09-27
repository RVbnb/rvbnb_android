package com.example.rvbnb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.rvbnb.R
import com.example.rvbnb.model.Land
import com.example.rvbnb.repo.App
import com.example.rvbnb.retro.RvApiInstance
import kotlinx.android.synthetic.main.activity_create_listing.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateListingActivity : AppCompatActivity() {

    companion object {
        internal const val REQUEST_IMAGE_GET = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_listing)
        title = "Add New Listing"

        // When users clicks on Add Listing, user will be brought back to the Homepage.
        btn_listing_add.setOnClickListener {
            val location = et_listing_address.text.toString()
            val description = et_listing_description.text.toString()
            val price = et_listing_price.text.toString()
            val url = et_listing_url.text.toString()

//            Code to check lot of potential user input problems and prevent app from crashing
            val regex = Regex("[.a-zA-Z]")
            if (location == "" || description == "" || price == "" || url == ""/* ||
                    price.contains(regex)*/){
                Toast.makeText(this, "Fields must not be blank, \n Url must be for a jpg, png, bmp, or jpeg \n Price only allows one decimal and numbers", Toast.LENGTH_LONG).show()

            }else if (url.contains(".jpg") || url.contains(".png") || url.contains(".bmp")
                || url.contains(".jpeg")){
                addListing(Land(LoginActivity.tokenAndId.id,
                    location,
                    description,
                    price.toDouble(),
                    url,
                    App.idCounter++))

                val addListingIntent = Intent(this, LandownerActivity::class.java)
                startActivity(addListingIntent)
            }
        }
    }

//    function adds land to the list of reservable Lands
    private fun addListing(land: Land) {
        val rvApi = RvApiInstance.createRvApi()
        rvApi.addLand(LoginActivity.tokenAndId.token, land).enqueue(object: Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.i("onResponse", "Successful")
            }

        })

    }

//    enables our ids to increment each time the app adds land or reservations
    override fun onDestroy() {
        super.onDestroy()
        App.savePreferences()
    }
}