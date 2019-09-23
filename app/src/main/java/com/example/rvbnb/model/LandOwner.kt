package com.example.rvbnb.model

import android.graphics.Bitmap

class LandOwner(val userName: String,
                val password: String,
                var displayName: String,
                var reserveRequests: MutableList<Plot>,
                var acceptedReservations: MutableList<Plot>)

class Plot(var name: String,
           var address: String,
           var costPerDay: String,
           var picture: Bitmap,
           var timeSlots: MutableList<TimeSlot>)

class TimeSlot(val date: String,
               var isReserved: Boolean)