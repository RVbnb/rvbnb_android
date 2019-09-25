package com.example.rvbnb.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.rvbnb.repo.LoginRepo

class LoginViewModel(context: Context): ViewModel() {

    private val loginRepo = LoginRepo(context)

    fun login(username: String, password: String, context: Context){
        loginRepo.loginUser(username, password, context)
    }
}