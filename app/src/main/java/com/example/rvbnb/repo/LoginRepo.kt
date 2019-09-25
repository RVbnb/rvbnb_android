package com.example.rvbnb.repo

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.rvbnb.db.DatabaseManagementInterface
import com.example.rvbnb.db.LandDB
import com.example.rvbnb.model.AcceptResponse
import com.example.rvbnb.model.Land
import com.example.rvbnb.model.UserAccount
import com.example.rvbnb.retro.RvApiInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class App: Application(){
    companion object{
        var repository: DatabaseManagementInterface? = null
    }

    override fun onCreate() {
        super.onCreate()
        repository = LoginRepo(applicationContext)
    }
}

class LoginRepo(context: Context): DatabaseManagementInterface {
    private val landDatabase by lazy {
        Room.databaseBuilder(context, LandDB::class.java, "land_db")
            .build() //TODO: .fallBackToDestructiveMigration()? How are we going to manage versions?
    }

    override fun buildLandList(): MutableList<Land> {
        return landDatabase.rvDao().buildLandList()
    }

    override fun createUserAccount(username: String, password: String, isLandOwner: Boolean) {
        val rvApi = RvApiInstance.createRvApi()
        val user = UserAccount(username, password, isLandOwner)
        rvApi.registerUser(user).enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    interface ResponseCallback{
        fun getAcceptResponse(acceptResponse: AcceptResponse)
        fun onFailureResponse()
    }

    var listener: ResponseCallback? = null

    override fun loginUser(username: String, password: String, context: Context) {
        listener = context as ResponseCallback
        val rvApi = RvApiInstance.createRvApi()
        val userLogin = UserAccount(username, password, false)
        rvApi.loginUser(userLogin).enqueue(object : Callback<AcceptResponse>{
            override fun onFailure(call: Call<AcceptResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<AcceptResponse>, response: Response<AcceptResponse>) {
                if (response.body() != null){
                    val acceptResponse = response.body() as AcceptResponse
                    listener?.getAcceptResponse(acceptResponse)
                }
                else{
                    listener?.onFailureResponse()
                }
            }
        })
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

    override fun cancelReservation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}