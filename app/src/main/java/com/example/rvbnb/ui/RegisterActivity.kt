package com.example.rvbnb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rvbnb.R
import com.example.rvbnb.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerViewModel = RegisterViewModel(this)

        // When user clicks on Submit button it will take user back to Login Page to login.
        btn_submit.setOnClickListener {

            registerViewModel.register(et_email.text.toString(), et_register_password.text.toString(), cb_register_landowner.isChecked)

            // If the Check Box for Landowner is checked, it will take user to the Login Page.
            if (cb_register_landowner.isChecked && !cb_register_rvowner.isChecked) {
                val registrationIntent = Intent(this, LoginActivity::class.java)
                startActivity(registrationIntent)
                // If the Check Box for RV Owner is checked, it will take user to the Login Page.
            } else if (cb_register_rvowner.isChecked && !cb_register_landowner.isChecked) {
                val registrationIntent = Intent(this, LoginActivity::class.java)
                startActivity(registrationIntent)
                // If both Check Boxes are selected, it will make a Toast and not take the user to the Login Page.
            } else if (cb_register_landowner.isChecked && cb_register_rvowner.isChecked) {
                Toast.makeText(this, "Please only select one", Toast.LENGTH_LONG).show()
                // If neither Check Boxes are selected, it will make a Toast and not take the user to the Login Page.
            } else {
                Toast.makeText(this, "Please select Landowner or RV Owner", Toast.LENGTH_LONG).show()
            }

            // TODO: Need to pass registration information to Profile Page.
            // TODO: Check to make sure passwords match.
        }
    }
}
