package com.example.rvbnb.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.rvbnb.model.Land

@Dao
interface RvDao {
    @Query("SELECT * FROM Land")
    fun buildLandList(): MutableList<Land>

    fun createUsersAccounts()
    fun updateUserProfile()
    fun deleteUsersAccounts()
    fun addPlot()
    fun updatePlot()
    fun removePlot()
    fun cancelRequest()
}