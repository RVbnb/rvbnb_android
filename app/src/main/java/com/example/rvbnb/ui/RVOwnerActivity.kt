package com.example.rvbnb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rvbnb.R
import kotlinx.android.synthetic.main.activity_rvowner.*

class RVOwnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rvowner)
        title = "RV Owner"

        btn_user_profile_rv.setOnClickListener {
            var profileIntent = Intent(this, ProfileActivity::class.java)
            startActivity(profileIntent)
        }
    }
}
