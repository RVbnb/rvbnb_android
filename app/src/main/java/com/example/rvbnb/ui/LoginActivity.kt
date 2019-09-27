package com.example.rvbnb.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import com.example.rvbnb.R
import com.example.rvbnb.model.AcceptResponse
import com.example.rvbnb.repo.App
import com.example.rvbnb.repo.LoginRepo
import com.example.rvbnb.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginRepo.LoginResponseCallback {

    companion object{
        lateinit var tokenAndId: AcceptResponse
    }

    fun isConnectionUp(): Boolean{
        val connectManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkConnection = connectManager.activeNetworkInfo
        return networkConnection.isConnected
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

        // Password is automatically hidden as user types it in.
        // When user clicks on the Show, it will show the password.
        // when user clicks on Hide, it will hide password.
        btn_show_hide.setOnClickListener {
            if (btn_show_hide.text.toString().equals("Show")) {
                et_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                btn_show_hide.text = "Hide"
            } else {
                et_password.transformationMethod = PasswordTransformationMethod.getInstance()
                btn_show_hide.text = "Show"
            }
        }

        // When user clicks on Log-In button with valid credentials, it will take user to Homepage.
        btn_login.setOnClickListener {
            loginViewModel.login(et_username.text.toString(), et_password.text.toString(), cb_landowner.isChecked)


//            val intent = Intent(this, RVOwnerActivity::class.java)
//            startActivity(intent)
//            if (!isConnectionUp()){
//                if (cb_landowner.isChecked){
//                    val intent = Intent(this, LandownerActivity::class.java)
//                    startActivity(intent)
//                }else{
//
//                }
//            }
        }

        // When user clicks on Register button it will take user to Registration Page.
        btn_register.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }


    //    enables our ids to increment each time the app adds land or reservations
    override fun onDestroy() {
        super.onDestroy()
        App.savePreferences()
    }
}