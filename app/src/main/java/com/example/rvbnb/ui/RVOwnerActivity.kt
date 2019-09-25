package com.example.rvbnb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rvbnb.R
import com.example.rvbnb.model.Land
import com.example.rvbnb.retro.RvApi
import kotlinx.android.synthetic.main.activity_rvowner.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RVOwnerActivity : AppCompatActivity(), Callback<Land> {

    lateinit var rvApi: RvApi

    override fun onFailure(call: Call<Land>, t: Throwable) {
        t.printStackTrace()
        val response = "Failure; ${t.printStackTrace()}"
        Toast.makeText(this@RVOwnerActivity, response, Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<Land>, response: Response<Land>) {
        if (response.isSuccessful) {

        } else {
            val response = "Response not succuessful; ${response.errorBody().toString()}"
            Toast.makeText(this@RVOwnerActivity, response, Toast.LENGTH_SHORT).show()
        }
    }

    // TODO: Need to figure out how to incorporate the token here? Do you need the token to do a search?
//    private fun searchLand(token: String) {
//        rvApi.getLands(token, )
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rvowner)
        title = "RV Owner"

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

        // When user clicks on Search Button, it will populate listings based on search.
        // TODO: Need to fix fun searchLand for this to work
//        iv_search_rv.setOnClickListener {
//            val searchText = et_search.text.toString()
//            searchLand(searchText)
//        }
    }
}
