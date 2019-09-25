package com.example.rvbnb.repo

import android.content.Context
import android.os.AsyncTask
import androidx.room.Room
import com.example.rvbnb.dao.RvDao
import com.example.rvbnb.db.DatabaseManagementInterface
import com.example.rvbnb.db.LandDB
import com.example.rvbnb.model.Land

class LandRepository(context: Context): DatabaseManagementInterface {

    private lateinit var rvDao: RvDao

    override fun buildLandList(): MutableList<Land> {
        return landDatabase.rvDao().buildLandList()
    }

    override fun createUserAccount(username: String, password: String, isLandOwner: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loginUser(username: String, password: String, isLandOwner: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserProfile() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addLand(land: Land) {
        val addLandAsyncTask = AddLandAsyncTask(rvDao).execute(land)
    }

    override fun updateLand(land: Land) {
        val updateLandAsyncTask = UpdateLandAsyncTask(rvDao).execute(land)
    }

    override fun removeLand(land: Land) {
        val removeLandAsyncTask = RemoveLandAsyncTask(rvDao).execute(land)
    }

    override fun cancelReservation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // Build the Room database
    private val landDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            LandDB::class.java, "land_db"
        ).fallbackToDestructiveMigration().build()
    }

    // AsyncTask
    companion object {
        private class AddLandAsyncTask(rvDao: RvDao) : AsyncTask<Land, Unit, Unit>() {
            override fun doInBackground(vararg p0: Land?) {
                RvDao.addLand(p0[0]!!)
            }
            val RvDao = rvDao
        }

        private class UpdateLandAsyncTask(rvDao: RvDao) : AsyncTask<Land, Unit, Unit>() {
            override fun doInBackground(vararg p0: Land?) {
                RvDao.updateLand(p0[0]!!)
            }
            val RvDao = rvDao
        }

        private class RemoveLandAsyncTask(rvDao: RvDao) : AsyncTask<Land, Unit, Unit>() {
            override fun doInBackground(vararg p0: Land?) {
                RvDao.removeLand(p0[0]!!)
            }
            val RvDao = rvDao
        }
    }
}
