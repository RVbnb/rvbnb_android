package com.example.rvbnb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rvbnb.R
import com.example.rvbnb.adapter.PlacesAdapter
import com.example.rvbnb.model.Land
import com.example.rvbnb.model.Reservation
import com.example.rvbnb.model.Reservations
import com.example.rvbnb.retro.RvApiInstance
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_rvowner.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsRVOwnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_rvowner)

        val displayLand: Land = intent.getSerializableExtra(PlacesAdapter.LAND_KEY) as Land

        tv_reserve_address_details.text = displayLand.location
        tv_reserve_price_details.text = displayLand.price_per_day.toString()
        tv_reserve_description_details.text = displayLand.description

        val rvApi = RvApiInstance.createRvApi()
        rvApi.getReservationsByLandId(LoginActivity.tokenAndId.token, displayLand.id)
            .enqueue(object : Callback<MutableList<Reservation>>{
                override fun onFailure(call: Call<MutableList<Reservation>>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<MutableList<Reservation>>,
                    response: Response<MutableList<Reservation>>
                ) {
                    if(response.body() != null){
                        val reservations = response.body()
                        reservations
                    }
                }
            })

        try {
            Picasso.get().load(displayLand.photo).into(iv_reserve_photo_details)
        }catch (e: Exception){
            Log.i("BadStuff", "Yep")
        }
    }
}
