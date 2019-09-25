package com.example.rvbnb.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rvbnb.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    var photoUri: Uri? = null

    companion object {
        internal const val REQUEST_IMAGE_GET = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        title = "Land Details"

        // When users clicks on Cancel, user will be brought back to the Homepage.
        btn_listing_cancel_details.setOnClickListener {
            val cancelIntent = Intent(this, CreateListingActivity::class.java)
            startActivity(cancelIntent)
        }
    }
}
