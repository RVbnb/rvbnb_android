package com.example.rvbnb.ui

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rvbnb.R
import com.example.rvbnb.adapter.PlacesAdapter
import com.example.rvbnb.adapter.ReservationsAdapter
import com.example.rvbnb.model.Land
import com.example.rvbnb.model.Reservation
import com.example.rvbnb.repo.App
import com.example.rvbnb.retro.RvApi
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

        showReservationsList(displayLand, rvApi)

        try {
            Picasso.get().load(displayLand.photo).into(iv_reserve_photo_details)
        }catch (e: Exception){
            Log.i("BadStuff", "Yep")
        }

        // Calendar Picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // When user clicks on the Calendar icon in the From box, a calendar will pop up so that the user can select the start date.
        iv_listing_calendar_from.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                et_reserve_availability_details_from.setText(""+ (mMonth+1) +"/"+ mDay +"/"+ mYear)
            }, year, month, day)
            dpd.show()
        }

        // When user clicks on the Calendar icon in the To box, a calendar will pop up so that the user can select the end date.
        iv_listing_calendar_to.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                et_reserve_availability_details_to.setText(""+ (mMonth+1) +"/"+ mDay +"/"+ mYear)
            }, year, month, day)
            dpd.show()
        }

        btn_reserve_details.setOnClickListener {
            rvApi.postReservation(LoginActivity.tokenAndId.token, displayLand.id, Reservation(
                App.idCounter++,
                displayLand.id,
                LoginActivity.tokenAndId.id,
                et_reserve_availability_details_from.text.toString(),
                et_reserve_availability_details_to.text.toString()))
                .enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Log.i("onResponse", "Successful")
                        showReservationsList(displayLand, rvApi)
                    }
                })
        }

        btn_reserve_delete_details.setOnClickListener {
            rvApi.deleteReservation(LoginActivity.tokenAndId.token, ReservationsAdapter.reservationId).enqueue(object: Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.i("onResponse", "Successful")
                    showReservationsList(displayLand, rvApi)
                }

            })
        }

    }

    fun showReservationsList(displayLand: Land, rvApi: RvApi){
        rvApi.getReservationsByLandId(LoginActivity.tokenAndId.token, displayLand.id)
            .enqueue(object : Callback<MutableList<Reservation>> {
                override fun onFailure(call: Call<MutableList<Reservation>>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<MutableList<Reservation>>,
                    response: Response<MutableList<Reservation>>
                ) {
                    if (response.body() != null) {
                        val reservations = response.body()
                        recycler_reservations.setHasFixedSize(true)
                        val manager = LinearLayoutManager(
                            this@DetailsRVOwnerActivity,
                            RecyclerView.VERTICAL,
                            false
                        )
                        recycler_reservations.layoutManager = manager
                        recycler_reservations.adapter = ReservationsAdapter(reservations!!)
                    }
                }
            })
    }


    //    enables our ids to increment each time the app adds land or reservations
    override fun onDestroy() {
        super.onDestroy()
        App.savePreferences()
    }
}
