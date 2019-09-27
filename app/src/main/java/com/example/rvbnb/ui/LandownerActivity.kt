package com.example.rvbnb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rvbnb.R
import com.example.rvbnb.adapter.PlacesAdapter
import com.example.rvbnb.model.Land
import com.example.rvbnb.repo.LoginRepo
import kotlinx.android.synthetic.main.activity_landowner.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LandownerActivity : AppCompatActivity()/*, Callback<Land>*/, LoginRepo.GetLandListCallback {

    // Gets listings
    override fun getList(mutableList: MutableList<Land>) {
        recyclerSetup(mutableList)
    }

    // Sets up the recyclerview so that it shows up in the LandownerActivity.
    private fun recyclerSetup(list: MutableList<Land>){
        rv_places_item.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_places_item.layoutManager = manager
        rv_places_item.adapter = PlacesAdapter(list)
    }

//    override fun onFailure(call: Call<Land>, t: Throwable) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun onResponse(call: Call<Land>, response: Response<Land>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landowner)
        title = "Landowner"

        val loginRepo = LoginRepo(this)
        loginRepo.retrieveLandList()

        // When user clicks on Profile Button, it will take user to Profile Page.
        btn_user_profile.setOnClickListener {
            val profileIntent = Intent(this, ProfileActivity::class.java)
            startActivity(profileIntent)
        }

        // When user clicks on + Button, it will take user to Create Listing Page.
        iv_add.setOnClickListener {
            val addIntent = Intent(this, CreateListingActivity::class.java)
            startActivity(addIntent)
        }

        // When user clicks on Logout Button, it will take user back to the Login Page.
        iv_logout.setOnClickListener {
            val logoutIntent = Intent(this, LoginActivity::class.java)
            startActivity(logoutIntent)
        }
    }
}
