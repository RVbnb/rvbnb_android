package com.example.rvbnb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.rvbnb.model.Land
import com.example.rvbnb.repo.LandRepository

class ViewModel (application: Application): AndroidViewModel(application) {

    private var repository: LandRepository = LandRepository(application)

    // TODO add LiveData
    private var landList: MutableList<Land> = repository.buildLandList()

    // TODO add LiveData
    fun buildLandList(): MutableList<Land> {
        return landList
    }

    fun createUsersAccounts() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun updateUserProfile() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun addLand(land: Land) {
        repository.addLand(land)
    }

    fun updateLand(land: Land) {
        repository.updateLand(land)
    }

    fun removeLand(land: Land) {
        repository.removeLand(land)
    }

    fun cancelReserve() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}