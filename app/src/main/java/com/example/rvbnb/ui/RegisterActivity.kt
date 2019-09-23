package com.example.rvbnb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rvbnb.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // When user clicks on Submit button it will take user back to Login Page to login.
        btn_submit.setOnClickListener {
            var registrationIntent = Intent(this, LoginActivity::class.java)
            startActivity(registrationIntent)
        }
    }
}
