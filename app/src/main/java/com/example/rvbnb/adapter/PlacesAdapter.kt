package com.example.rvbnb.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rvbnb.R
import com.example.rvbnb.model.Land
import com.example.rvbnb.ui.DetailsActivity
import com.example.rvbnb.ui.DetailsRVOwnerActivity
import com.example.rvbnb.ui.RVOwnerActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.places_item.view.*

class PlacesAdapter(private val listPlaces: MutableList<Land>): RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val landPicture = view.iv_listing_listview
        var landLocation = view.tv_listing_address_listview
        var landDescription = view.tv_listing_description_listview
        val card = view.ll_listings
    }

    companion object{
        const val LAND_KEY = "LAND_KEY"
    }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context)
            .inflate(R.layout.places_item, parent, false)
        context = parent.context
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return listPlaces.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = listPlaces[position]
//        holder.landPicture.setImageURI(Uri.parse(place.photo))

        holder.landLocation.text = place.location
        holder.landDescription.text = place.description

        try {
            Picasso.get().load(place.photo).into(holder.landPicture)
        }catch (e: Exception){
            Log.i("BadStuff", "Yep")
        }

//        sets intents of which activities to go to when land is selected and sends
//        land object to the respective detail activities
        holder.card.setOnClickListener {
            lateinit var intent: Intent
            if (context is RVOwnerActivity){
                intent = Intent(context, DetailsRVOwnerActivity::class.java)
                intent.putExtra(LAND_KEY, place)
                context.startActivity(intent)
            }else{
                intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra(LAND_KEY, place)
                context.startActivity(intent)
            }

        }
    }
}