package com.example.rvbnb.viewmodel

import android.content.Context
import com.example.rvbnb.repo.LoginRepo

class RegisterViewModel(context: Context) {

    private val loginRepo = LoginRepo(context)

    fun register(username: String, password: String, isLandOwner: Boolean){
        loginRepo.createUserAccount(username, password, isLandOwner)
    }
}