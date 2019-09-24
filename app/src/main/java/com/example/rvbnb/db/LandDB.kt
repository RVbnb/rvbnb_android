package com.example.rvbnb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rvbnb.dao.RvDao
import com.example.rvbnb.model.Land

@Database(entities = [Land::class], version = 1, exportSchema = false)
abstract class LandDB: RoomDatabase() {
    abstract fun rvDao(): RvDao
}