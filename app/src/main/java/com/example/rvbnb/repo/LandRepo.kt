package com.example.rvbnb.repo

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.rvbnb.db.DatabaseManagementInterface
import com.example.rvbnb.db.LandDB
import com.example.rvbnb.model.Land

class App: Application(){
    companion object{
        var repository: DatabaseManagementInterface? = null
    }

    override fun onCreate() {
        super.onCreate()
        repository = LandRepo(applicationContext)
    }
}

class LandRepo(context: Context): DatabaseManagementInterface {
    private val landDatabase by lazy {
        Room.databaseBuilder(context, LandDB::class.java, "land_db")
            .build() //TODO: .fallBackToDestructiveMigration()? How are we going to manage versions?
    }

    override fun buildLandList(): MutableList<Land> {
        return landDatabase.rvDao().buildLandList()
    }

    override fun createUserAccount() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserProfile() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addLand(land: Land) {
        landDatabase.rvDao().addLand(land)
    }

    override fun updateLand(land: Land) {
        landDatabase.rvDao().updateLand(land)
    }

    override fun removeLand(land: Land) {
        landDatabase.rvDao().removeLand(land)
    }

    override fun cancelReserve() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}