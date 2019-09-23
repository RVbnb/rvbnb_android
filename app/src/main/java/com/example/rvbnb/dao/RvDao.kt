package com.example.rvbnb.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.rvbnb.model.Plot

@Dao
interface RvDao {
    @Query("SELECT * FROM Plot")
    fun buildPlotList(): MutableList<Plot>
}