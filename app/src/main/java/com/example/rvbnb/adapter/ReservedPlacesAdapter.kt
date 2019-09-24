package com.example.rvbnb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rvbnb.R
import com.example.rvbnb.model.Land
import kotlinx.android.synthetic.main.reserved_places_item.view.*

class ReservedPlacesAdapter(private val username: String,
                            private val reservedPlaces: MutableList<Land>):
    RecyclerView.Adapter<ReservedPlacesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textName: TextView = view.text_name
        val textDate: TextView = view.text_date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reserved_places_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = reservedPlaces.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = reservedPlaces[position]

        holder.textName.text = place.name

        place.timeSlot?.forEach {
            if (it.userName == username){
                if (it.date != null){
                    holder.textDate.text = it.date
                }else{
                    val dateRange = it.startDate + " to " + it.endDate
                    holder.textDate.text = dateRange
                }
            }
        }
    }
}

//Add a username variable to timeSlot, so the specific times slot to display can be grabbed
//Make the timeSlot variable in Land a mutableList of TimeSlot