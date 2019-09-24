package com.example.rvbnb.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

class LandOwner(val userName: String,
                val password: String,
                var displayName: String,
                var reserveRequests: MutableList<Land>,
                var acceptedReservations: MutableList<Land>)

@Entity
class Land(var name: String,
           var address: String,
           @PrimaryKey
           var ownerId: String,
           var costPerDay: String,
           var picture: Bitmap,
           var dates: String,
           var timeSlot: MutableList<TimeSlot>?)

class TimeSlot{
    var startDate: String? = null
    var endDate: String? = null
    var date: String? = null
    var userName: String? = null

    constructor(username: String,
                startDate: String,
                endDate: String){
        this.userName = username
        this.startDate = startDate
        this.endDate = endDate
    }


    constructor(userName: String,
                date: String){
        this.userName = userName
        this.date = date
    }
}