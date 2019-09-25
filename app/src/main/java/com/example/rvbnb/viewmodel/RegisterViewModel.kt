package com.example.rvbnb.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.CheckBox
import android.widget.Toast
import com.example.rvbnb.repo.LoginRepo
import com.example.rvbnb.ui.LoginActivity

class RegisterViewModel(private val context: Context) {

    private val loginRepo = LoginRepo(context)

    fun register(username: String, password: String, isLandOwner: Boolean){
        loginRepo.createUserAccount(username, password, isLandOwner)
    }

    fun checkCheckboxes(cbLandowner: CheckBox, cbRvOwner: CheckBox){
        // If the Check Box for Landowner is checked, it will take user to the Landowner Homepage.
        if (cbLandowner.isChecked && !cbRvOwner.isChecked) {
            val registrationIntent = Intent(context, LoginActivity::class.java)
            context.startActivity(registrationIntent)
            // If the Check Box for RV Owner is checked, it will take user to the RV Owner Homepage.
        } else if (cbRvOwner.isChecked && !cbLandowner.isChecked) {
            val registrationIntent = Intent(context, LoginActivity::class.java)
            context.startActivity(registrationIntent)
            // If both Check Boxes are selected, it will make a Toast and not take the user to the Homepage.
        } else if (cbLandowner.isChecked && cbRvOwner.isChecked) {
            Toast.makeText(context, "Please only select one", Toast.LENGTH_LONG).show()
            // If neither Check Boxes are selected, it will make a Toast and not take the user to the Homepage.
        } else {
            Toast.makeText(context, "Please select Landowner or RV Owner", Toast.LENGTH_LONG).show()
        }
    }
}