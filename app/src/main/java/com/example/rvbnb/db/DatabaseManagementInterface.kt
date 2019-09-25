package com.example.rvbnb.db

import android.content.Context
import com.example.rvbnb.model.AcceptResponse
import com.example.rvbnb.model.Land

interface DatabaseManagementInterface {
    fun buildLandList(): MutableList<Land>
    fun createUserAccount(username: String, password: String, isLandOwner: Boolean)
    fun loginUser(username: String, password: String, context: Context)
    fun updateUserProfile()
    fun addLand(land: Land)
    fun updateLand(land: Land)
    fun removeLand(land: Land)
    fun cancelReservation()
}