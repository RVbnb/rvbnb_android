package com.example.rvbnb.dao

import androidx.room.*
import com.example.rvbnb.model.Land

@Dao
interface RvDao {
    @Query("SELECT * FROM Land")
    fun buildLandList(): MutableList<Land>

    @Insert
    fun addLand(land: Land)

    @Update
    fun updateLand(land: Land)

    @Delete
    fun removeLand(land: Land)
}