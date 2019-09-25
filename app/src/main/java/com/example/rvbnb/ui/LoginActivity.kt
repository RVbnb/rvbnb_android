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

class LoginActivity : AppCompatActivity(), LoginRepo.LoginResponseCallback {

    companion object{
        lateinit var tokenAndId: AcceptResponse
    }

    override fun getAcceptResponse(acceptResponse: AcceptResponse) {
        tokenAndId = acceptResponse
        loginViewModel.checkCheckboxes(cb_landowner, cb_rvowner)
    }

    override fun onFailureResponse() {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
    }

    lateinit var loginViewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel = LoginViewModel(this)

        // When user clicks on Log-In button with valid credentials, it will take user to Homepage.
        btn_login.setOnClickListener {
            loginViewModel.login(et_username.text.toString(), et_password.text.toString(), cb_landowner.isChecked)
        }

        // When user clicks on Register button it will take user to Registration Page.
        btn_register.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }
}