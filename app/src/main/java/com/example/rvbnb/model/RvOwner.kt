package com.example.rvbnb.model

class RvOwner(val username: String,
              val password: String,
              val is_land_owner: Boolean,
              var reservedLocations: MutableList<Land>?)