package com.example.rvbnb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rvbnb.R
import com.example.rvbnb.model.Reservation
import kotlinx.android.synthetic.main.reservations_item.view.*

class ReservationsAdapter(private val reservations: MutableList<Reservation>):
    RecyclerView.Adapter<ReservationsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textDates: TextView = view.txt_dates_reserved
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reservations_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = reservations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reservation = reservations[position]

        //Setup reserved date(s) to be displayed in Recycler Item.
        val dateRange = reservation.reserve_date_start + " to " + reservation.reserve_date_end
        holder.textDates.text = dateRange
    }
}