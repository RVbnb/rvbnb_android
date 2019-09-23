package com.example.rvbnb.db

interface DatabaseManagementInterface {
    fun createUsersAccounts()
    fun updateUserProfile()
    fun deleteUsersAccounts()
    fun addPlot()
    fun updatePlot()
    fun removePlot()
    fun cancelRequest()
}