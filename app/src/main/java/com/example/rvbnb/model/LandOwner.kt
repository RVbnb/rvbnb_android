package com.example.rvbnb.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

class LandOwner(val userName: String,
                val password: String,
                var displayName: String,
                var reserveRequests: MutableList<Plot>,
                var acceptedReservations: MutableList<Plot>)

@Entity
class Plot(var name: String,
           var address: String,
           @PrimaryKey
           var ownerId: String,
           var costPerDay: String,
           var picture: Bitmap,
           var timeSlots: MutableList<TimeSlot>)

class TimeSlot(val date: String,
               var isReserved: Boolean)