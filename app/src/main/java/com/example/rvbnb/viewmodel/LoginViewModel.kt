package com.example.rvbnb.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.CheckBox
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.rvbnb.repo.LoginRepo
import com.example.rvbnb.ui.LandownerActivity
import com.example.rvbnb.ui.RVOwnerActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginViewModel(private val context: Context): ViewModel() {

    private val loginRepo = LoginRepo(context)

    fun login(username: String, password: String, isLandOwner: Boolean){
        loginRepo.loginUser(username, password, isLandOwner)
    }

    fun checkCheckboxes(cbLandowner: CheckBox, cbRvOwner: CheckBox){
        // If the Check Box for Landowner is checked, it will take user to the Landowner Homepage.
        if (cbLandowner.isChecked && !cbRvOwner.isChecked) {
            val logLandownerIntent = Intent(context, LandownerActivity::class.java)
            context.startActivity(logLandownerIntent)
            // If the Check Box for RV Owner is checked, it will take user to the RV Owner Homepage.
        } else if (cbRvOwner.isChecked && !cbLandowner.isChecked) {
            val logRVOwnerIntent = Intent(context, RVOwnerActivity::class.java)
            context.startActivity(logRVOwnerIntent)
            // If both Check Boxes are selected, it will make a Toast and not take the user to the Homepage.
        } else if (cbLandowner.isChecked && cbRvOwner.isChecked) {
            Toast.makeText(context, "Please only select one", Toast.LENGTH_LONG).show()
            // If neither Check Boxes are selected, it will make a Toast and not take the user to the Homepage.
        } else {
            Toast.makeText(context, "Please select Landowner or RV Owner", Toast.LENGTH_LONG).show()
        }
    }
}