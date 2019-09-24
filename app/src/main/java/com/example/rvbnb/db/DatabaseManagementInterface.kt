package com.example.rvbnb.db

import com.example.rvbnb.model.Land

interface DatabaseManagementInterface {
    fun buildLandList(): MutableList<Land>
    fun createUsersAccounts()
    fun updateUserProfile()
    fun addLand(land: Land)
    fun updateLand(land: Land)
    fun removeLand(land: Land)
    fun cancelReserve()
}