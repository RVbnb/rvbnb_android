package com.example.rvbnb.model

import androidx.room.Entity
import androidx.room.PrimaryKey

class LandOwner(val username: String,
                val password: String,
                val is_land_owner: Boolean,
                var reservedLands: MutableList<Land>?)

@Entity
class Land(@PrimaryKey
           var id: Int,
           var owner_id: Int,
           var location: String,
           var description: String,
           var price_per_day: Double,
           var photo: String/*, //Change to Url once grabbed
           var dates: String, //TODO: this variable type needs to be changed?
           var timeSlot: MutableList<TimeSlot>?*/) //Made timeSlot nullable so LandOwners can add
                                                 //Land without reserving timeSlots

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