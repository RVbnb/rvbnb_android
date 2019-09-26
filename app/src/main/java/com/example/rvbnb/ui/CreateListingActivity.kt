package com.example.rvbnb.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rvbnb.R
import com.example.rvbnb.model.Land
import com.example.rvbnb.retro.RvApiInstance
import kotlinx.android.synthetic.main.activity_create_listing.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateListingActivity : AppCompatActivity() {

    lateinit var listing: Land

    var photoUri: Uri? = null

    companion object {
        internal const val REQUEST_IMAGE_GET = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_listing)
        title = "Add New Listing"

        // When user clicks on Image Placeholder, user can select a photo.
        iv_listing.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent,
                    REQUEST_IMAGE_GET
                )
            }
        }

        // When users clicks on Add Listing, user will be brought back to the Homepage.
        btn_listing_add.setOnClickListener {

            // TODO: Need to have data that was entered to be saved and populated on the Homepage.
            val addListingIntent = Intent(this, LandownerActivity::class.java)
            startActivity(addListingIntent)
        }
    }

    // The photo that was selected by the user will be set.
    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            val uri = data!!.data
            photoUri = uri
            iv_listing.setImageURI(uri)
        }
    }

    private fun addListing() {
        val rvApi = RvApiInstance.createRvApi()
        rvApi.addLand(LoginActivity.tokenAndId.token, listing).enqueue(object: Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.i("onResponse", "Successul")
            }

        })

    }
}