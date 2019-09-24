package com.example.rvbnb.model

class RvOwner(val username: String,
              val password: String,
              val is_land_owner: Boolean,
              var displayName: String,
              var reserveRequests: MutableList<Land>,
              var reservedLocations: MutableList<Land>)