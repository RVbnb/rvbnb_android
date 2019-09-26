package com.example.rvbnb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rvbnb.R
import com.example.rvbnb.adapter.PlacesAdapter
import com.example.rvbnb.model.Land
import kotlinx.android.synthetic.main.activity_details_rvowner.*

class DetailsRVOwnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_rvowner)

        val displayLand: Land = intent.getSerializableExtra(PlacesAdapter.LAND_KEY) as Land

        tv_reserve_address_details.text = displayLand.location
        tv_reserve_price_details.text = displayLand.price_per_day.toString()
        tv_reserve_description_details.text = displayLand.description
        iv_reserve_photo_details
    }
}
