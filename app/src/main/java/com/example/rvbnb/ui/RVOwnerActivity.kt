package com.example.rvbnb.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rvbnb.R
import com.example.rvbnb.adapter.PlacesAdapter
import com.example.rvbnb.model.Land
import com.example.rvbnb.repo.AddLandAsync
import com.example.rvbnb.repo.App
import com.example.rvbnb.repo.BuildAsyncTask
import com.example.rvbnb.repo.LoginRepo
import com.example.rvbnb.retro.RvApi
import kotlinx.android.synthetic.main.activity_rvowner.*
import kotlinx.android.synthetic.main.places_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RVOwnerActivity : AppCompatActivity(),/*Callback<Land>,*/  LoginRepo.GetLandListCallback, BuildAsyncTask.CreateLandList {

    lateinit var rvApi: RvApi

    private var landToSearch = mutableListOf<Land>()

    fun isConnectionUp(): Boolean{
        val connectManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkConnection = connectManager.activeNetworkInfo
        return networkConnection.isConnected
    }

// Gets Land from backend
    override fun getList(mutableList: MutableList<Land>) {
        if (isConnectionUp()){
            recyclerSetup(mutableList)
            landToSearch = mutableList
            mutableList.forEach {
                AddLandAsync(it).execute()
            }
        }
    }

    // Gets Land from SQL CRUD(persistence, internal storage)
    override fun getLandList(landList: MutableList<Land>) {
        recyclerSetup(landList)
        landToSearch = landList
//        if(!isConnectionUp()){
//
//        }
    }

    private fun recyclerSetup(list: MutableList<Land>){
        recycler_rv.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_rv.layoutManager = manager
        recycler_rv.adapter = PlacesAdapter(list)
    }

//    override fun onFailure(call: Call<Land>, t: Throwable) {
//        t.printStackTrace()
//        val response = "Failure; ${t.printStackTrace()}"
//        Toast.makeText(this@RVOwnerActivity, response, Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onResponse(call: Call<Land>, response: Response<Land>) {
//        if (response.isSuccessful) {
//
//        } else {
//            val response = "Response not succuessful; ${response.errorBody().toString()}"
//            Toast.makeText(this@RVOwnerActivity, response, Toast.LENGTH_SHORT).show()
//        }
//    }

    // TODO: Need to figure out how to incorporate the token here? Do you need the token to do a search?
//    private fun searchLand(token: String) {
//        rvApi.getLands(token, )
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rvowner)
        title = "RV Owner"

        val loginRepo = LoginRepo(this)
        loginRepo.retrieveLandList()

        // When user clicks on Profile Button, it will take user to Profile Page.
        btn_user_profile_rv.setOnClickListener {
            val profileIntent = Intent(this, ProfileActivity::class.java)
            startActivity(profileIntent)
        }

        // When user clicks on Logout Button, it will take user back to the Login Page.
        iv_logout_rv.setOnClickListener {
            val logoutIntent = Intent(this, LoginActivity::class.java)
            startActivity(logoutIntent)
        }

        BuildAsyncTask(this).execute()

        iv_search_rv.setOnClickListener{
            val userSearch = et_search.text.toString()
            var mySearchList = mutableListOf<Land>()
            landToSearch.forEach {
                if(it.location.contains(userSearch)){
                    mySearchList.add(it)
                }
            }
            recyclerSetup(mySearchList)
        }

        // When user clicks on Search Button, it will populate listings based on search.
        // TODO: Need to fix fun searchLand for this to work
//        iv_search_rv.setOnClickListener {
//            val searchText = et_search.text.toString()
//            searchLand(searchText)
//        }
    }

    // TODO: Need to add function to click on cardview to see details of the land.
    fun onDetailsRVOwner (land: Land) {
        llayout_places.setOnClickListener {
            val viewListingIntent = Intent(this, DetailsRVOwnerActivity::class.java)
            intent.putExtra("LAND_ENTRY_ID", land.id)
            startActivity(viewListingIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        App.savePreferences()
    }
}
