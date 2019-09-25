package com.example.rvbnb.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.rvbnb.repo.LandRepo

class LoginViewModel(context: Context): ViewModel() {

    val landRepo = LandRepo(context)



    fun login(username: String, password: String, context: Context){
        landRepo.loginUser(username, password, context)
    }
}