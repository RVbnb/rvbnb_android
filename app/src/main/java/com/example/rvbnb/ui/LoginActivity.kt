package com.example.rvbnb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rvbnb.R
import com.example.rvbnb.model.AcceptResponse
import com.example.rvbnb.repo.LoginRepo
import com.example.rvbnb.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginRepo.ResponseCallback {

    companion object{
        lateinit var tokenAndId: AcceptResponse
    }

    override fun getAcceptResponse(acceptResponse: AcceptResponse) {
        tokenAndId = acceptResponse
    }

    lateinit var loginViewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel = LoginViewModel(this)

        // When user clicks on Log-In button, it will take user to Homepage.
        btn_login.setOnClickListener {

            loginViewModel.login(et_username.text.toString(), et_password.text.toString(), this)

            // If the Check Box for Landowner is checked, it will take user to the Landowner Homepage.
            if (cb_landowner.isChecked && !cb_rvowner.isChecked) {
                val logLandownerIntent = Intent(this, LandownerActivity::class.java)
                startActivity(logLandownerIntent)
                // If the Check Box for RV Owner is checked, it will take user to the RV Owner Homepage.
            } else if (cb_rvowner.isChecked && !cb_landowner.isChecked) {
                val logRVOwnerIntent = Intent(this, RVOwnerActivity::class.java)
                startActivity(logRVOwnerIntent)
                // If both Check Boxes are selected, it will make a Toast and not take the user to the Homepage.
            } else if (cb_landowner.isChecked && cb_rvowner.isChecked) {
                Toast.makeText(this, "Please only select one", Toast.LENGTH_LONG).show()
                // If neither Check Boxes are selected, it will make a Toast and not take the user to the Homepage.
            } else {
                Toast.makeText(this, "Please select Landowner or RV Owner", Toast.LENGTH_LONG).show()
            }
        }

        // When user clicks on Register button it will take user to Registration Page.
        btn_register.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }
}
