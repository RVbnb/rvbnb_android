package com.example.rvbnb.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
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
import com.example.rvbnb.retro.RvApiInstance
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details_rvowner.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream

class DetailsActivity : AppCompatActivity() {

    var photoUri: Uri? = null

    companion object {
        internal const val REQUEST_IMAGE_GET = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        title = "Land Details"

        val rvApi = RvApiInstance.createRvApi()
        val displayLand: Land = intent.getSerializableExtra(PlacesAdapter.LAND_KEY) as Land

        et_listing_address_details.setText(displayLand.location)
        //et_listing_availability_details.setText(displayLand.)
        et_listing_description_details.setText(displayLand.description)
        et_listing_price_details.setText(displayLand.price_per_day.toString())

//        var urlString = "https://scx1.b-cdn.net/csz/news/800/2018/3-ocean.jpg"


        btn_listing_update_details.setOnClickListener {
            val landUpdate = Land(LoginActivity.tokenAndId.id, 
                et_listing_address_details.text.toString(),
                et_listing_description_details.text.toString(),
                et_listing_price_details.text.toString().toDouble(),
                et_listing_url_details.text.toString())
            rvApi.updateLand(LoginActivity.tokenAndId.token, displayLand.id, landUpdate).enqueue(object: Callback<Void>{
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.i("We had a success!", "Nope")
                    //https://img-aws.ehowcdn.com/700x/cdn.onlyinyourstate.com/wp-content/uploads/2015/06/a63-700x467.jpg
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.i("We had a success!", "Yup")
                }
            })
        }


        rvApi.getReservationsByLandId(LoginActivity.tokenAndId.token, displayLand.id)
            .enqueue(object : Callback<MutableList<Reservation>> {
                override fun onFailure(call: Call<MutableList<Reservation>>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<MutableList<Reservation>>,
                    response: Response<MutableList<Reservation>>
                ) {
                    if(response.body() != null){
                        val reservations = response.body()
                        recycler_reservations_landowner.setHasFixedSize(true)
                        val manager = LinearLayoutManager(this@DetailsActivity, RecyclerView.VERTICAL, false)
                        recycler_reservations_landowner.layoutManager = manager
                        recycler_reservations_landowner.adapter = ReservationsAdapter(reservations!!)
                    }
                }
            })

        try {
            Picasso.get().load(displayLand.photo).into(iv_listing_details)
        }catch (e: Exception){
            Log.i("ImageLinkBad", "True", e)
        }


        // When user clicks on photo, user can select another photo to update with.
        iv_listing_details.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent,
                    REQUEST_IMAGE_GET
                )
            }
        }

        // When users clicks on Cancel, user will be brought back to the Homepage.
        btn_listing_cancel_details.setOnClickListener {
            val cancelIntent = Intent(this, LandownerActivity::class.java)
            startActivity(cancelIntent)
        }

        btn_listing_update_details.setOnClickListener {
            // TODO: add code for all of the parts that could be updated
            val updateIntent = Intent(this, LandownerActivity::class.java)
            startActivity(updateIntent)
        }

        btn_listing_delete_details.setOnClickListener {
            // TODO: .removeAt(id)
            val deleteIntent = Intent(this, LandownerActivity::class.java)
            startActivity(deleteIntent)
        }
    }

    // The photo that was selected by the user will be set.
    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            val uri = data!!.data
            photoUri = uri
            iv_listing_details.setImageURI(uri)
        }
    }
}
