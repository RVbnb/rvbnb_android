package com.example.rvbnb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rvbnb.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // When user clicks on Log-In button it will take user to Homepage.
        btn_login.setOnClickListener {
            if (cb_landowner.isChecked) {
                var logLandownerIntent = Intent(this, LandownerActivity::class.java)
                startActivity(logLandownerIntent)
            } else {
                var logRVOwnerIntent = Intent(this, RVOwnerActivity::class.java)
                startActivity(logRVOwnerIntent)
            }
        }

        // When user clicks on Register button it will take user to Registration Page.
        btn_register.setOnClickListener {
            var registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }
}
