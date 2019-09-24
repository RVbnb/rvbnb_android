package com.example.rvbnb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rvbnb.R
import kotlinx.android.synthetic.main.activity_landowner.*

class LandownerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landowner)
        title = "Landowner"

        // When user clicks on Profile Button, it will take user to Profile Page.
        btn_user_profile.setOnClickListener {
            var profileIntent = Intent(this, ProfileActivity::class.java)
            startActivity(profileIntent)
        }
    }
}
