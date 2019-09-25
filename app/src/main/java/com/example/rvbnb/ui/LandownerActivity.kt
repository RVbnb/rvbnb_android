package com.example.rvbnb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rvbnb.R
import kotlinx.android.synthetic.main.activity_landowner.*

class LandownerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landowner)
        title = "Landowner"

        rv_places_item.layoutManager = LinearLayoutManager(this@LandownerActivity)
        rv_places_item.setHasFixedSize(true)

        // When user clicks on Profile Button, it will take user to Profile Page.
        btn_user_profile.setOnClickListener {
            val profileIntent = Intent(this, ProfileActivity::class.java)
            startActivity(profileIntent)
        }

        // When user clicks on + Button, it will take user to Create Listing Page.
        iv_add.setOnClickListener {
            val addIntent = Intent(this, CreateListingActivity::class.java)
            startActivity(addIntent)
        }

        // When user clicks on Logout Button, it will take user back to the Login Page.
        iv_logout.setOnClickListener {
            val logoutIntent = Intent(this, LoginActivity::class.java)
            startActivity(logoutIntent)
        }
    }
}
