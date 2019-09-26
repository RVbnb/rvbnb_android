package com.example.rvbnb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

class UserAccount(val username: String,
                  val password: String,
                  val is_land_owner: Boolean)

class AcceptResponse(val token: String, val id: Int, username: String, val is_land_owner: Boolean)

@Entity
class Land(var owner_id: Int,
           var location: String,
           var description: String,
           var price_per_day: Double,
           var photo: String,
           @PrimaryKey(autoGenerate = true)
           var id: Int = 101/*, //Change to Url once grabbed
           var dates: String, //TODO: this variable type needs to be changed?
           var timeSlot: MutableList<Reservation>?*/): Serializable //Made timeSlot nullable so LandOwners can add
                                                 //Land without reserving timeSlots

class Reservations(listing: Land, reservations: MutableList<Reservation>)

class Reservation(var id: Int, var listing_id: Int, var user_id: Int, var reserve_date_start: String, var reserve_date_end: String)

//class Reservation{
//    var startDate: String? = null
//    var endDate: String? = null
//    var date: String? = null
//    var userName: String? = null
//
//    constructor(username: String,
//                startDate: String,
//                endDate: String){
//        this.userName = username
//        this.startDate = startDate
//        this.endDate = endDate
//    }
//
//
//    constructor(userName: String,
//                date: String){
//        this.userName = userName
//        this.date = date
//    }
//}