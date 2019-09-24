package com.example.rvbnb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rvbnb.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // When user clicks on Log-In button, it will take user to Homepage.
        btn_login.setOnClickListener {

            // If the Check Box for Landowner is checked, it will take user to the Landowner Homepage.
            if (cb_landowner.isChecked) {
                var logLandownerIntent = Intent(this, LandownerActivity::class.java)
                startActivity(logLandownerIntent)
                // If the Check Box for RV Owner is checked, it will take user to the RV Owner Homepage.
            } else if (cb_rvowner.isChecked) {
                var logRVOwnerIntent = Intent(this, RVOwnerActivity::class.java)
                startActivity(logRVOwnerIntent)
                // If neither Check Boxes are selected, it will not take the user to the Homepage.
            } else {
                Toast.makeText(this, "Please select Landowner or RV Owner", Toast.LENGTH_LONG).show()
            }
        }

        // When user clicks on Register button it will take user to Registration Page.
        btn_register.setOnClickListener {
            var registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }
}
