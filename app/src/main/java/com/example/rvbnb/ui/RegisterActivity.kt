package com.example.rvbnb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rvbnb.R
import com.example.rvbnb.repo.App
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

            registerViewModel.checkCheckboxes(cb_register_landowner, cb_register_rvowner)

            if (cb_register_landowner != cb_register_rvowner){
                if (et_register_password.text.toString() == et_register_confirm_password.text.toString()){
                    registerViewModel
                        .register(et_email.text.toString(),
                                  et_register_password.text.toString(),
                                  cb_register_landowner.isChecked)
                }else{
                    Toast.makeText(this, "Password fields must match.", Toast.LENGTH_SHORT).show()
                }
            }

            // TODO: Need to pass registration information to Profile Page.
            // TODO: Make blank user invalid option.
        }
    }


    //    enables our ids to increment each time the app adds land or reservations
    override fun onDestroy() {
        super.onDestroy()
        App.savePreferences()
    }
}
