package com.example.rvbnb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rvbnb.R
import com.example.rvbnb.model.Land
import kotlinx.android.synthetic.main.places_item.view.*

class PlacesAdapter(private val listPlaces: MutableList<Land>): RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val landName: TextView = view.tv_listing_name_listview
        //val landPicture: Picasso.get().load(land.photo).into(iv_listing_listview)
        var landLocation: TextView = view.tv_listing_address_listview
        var landDescription: TextView = view.tv_listing_description_listview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context)
            .inflate(R.layout.places_item, parent, false)
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return listPlaces.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = listPlaces[position]
//        holder.landLocation = place.location
//        holder.landDescription = place.description
    }
}