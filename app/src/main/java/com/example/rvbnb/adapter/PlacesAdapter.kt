package com.example.rvbnb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rvbnb.R
import com.example.rvbnb.model.Land
import kotlinx.android.synthetic.main.places_item.view.*

class PlacesAdapter(private val reservedPlaces: MutableList<Land>): RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val text: TextView = view.text_place
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reserved_places_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = reservedPlaces.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var place = reservedPlaces[position]

        holder.text.text = place.id.toString()
    }
}