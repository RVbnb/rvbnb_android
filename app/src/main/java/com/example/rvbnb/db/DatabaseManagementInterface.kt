package com.example.rvbnb.db

interface DatabaseManagementInterface {
    fun createUsersAccounts()
    fun updateUserProfile()
    fun addLand()
    fun updateLand()
    fun removeLand()
    fun cancelReserve()
}