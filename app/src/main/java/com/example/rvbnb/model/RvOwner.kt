package com.example.rvbnb.model

class RvOwner(val userName: String,
              val password: String,
              var displayName: String,
              var reserveRequests: MutableList<Plot>,
              var reservedLocations: MutableList<Plot>)