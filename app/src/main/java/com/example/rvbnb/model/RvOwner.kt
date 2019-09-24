package com.example.rvbnb.model

class RvOwner(val userName: String,
              val password: String,
              var displayName: String,
              var reserveRequests: MutableList<Land>,
              var reservedLocations: MutableList<Land>)