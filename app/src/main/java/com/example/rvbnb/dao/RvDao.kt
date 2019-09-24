package com.example.rvbnb.dao

import androidx.room.*
import com.example.rvbnb.model.Land

@Dao
interface RvDao {
    @Query("SELECT * FROM Land")
    fun buildLandList(): MutableList<Land>

    @Insert
    fun addLand()

    @Update
    fun updateLand()

    @Delete
    fun removeLand()
}