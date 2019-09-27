package com.example.rvbnb.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rvbnb.R
import com.example.rvbnb.model.Reservation
import com.example.rvbnb.ui.LoginActivity
import kotlinx.android.synthetic.main.reservations_item.view.*

class ReservationsAdapter(private val reservations: MutableList<Reservation>):
    RecyclerView.Adapter<ReservationsAdapter.ViewHolder>() {

    companion object{
        var reservationId = 0
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textDates: TextView = view.txt_dates_reserved
        val content: LinearLayout = view.ll_reservations
    }

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reservations_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = reservations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reservation = reservations[position]

//        color changes on reservations when clicked. Last selection is selected item.
        holder.content.setBackgroundColor(ContextCompat.getColor(context, android.R.color.background_light))


        holder.content.setOnClickListener {
            if (!LoginActivity.tokenAndId.is_land_owner){
                reservationId = reservation.id
                holder.content.setBackgroundColor(Color.CYAN)
            }
        }

        //Setup reserved date(s) to be displayed in Recycler Item.
        val dateRange = reservation.reserve_date_start + " to " + reservation.reserve_date_end
        holder.textDates.text = dateRange
    }
}