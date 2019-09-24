package com.example.rvbnb.retro

import com.example.rvbnb.model.AcceptResponse
import com.example.rvbnb.model.Land
import com.example.rvbnb.model.LandOwner
import com.example.rvbnb.model.RvOwner
import retrofit2.Call
import retrofit2.http.*

interface RvApi {
    @POST("/api/auth/register")
    fun registerUser(@Body landOwner: LandOwner)//: Call<LandOwner>

    @POST("/api/auth/login")
    fun loginUser(@Body landOwner: LandOwner): Call<AcceptResponse>

    @POST(" /api/listings")
    fun registerLand(@Body land: Land,
                     @Header("Authorization")token: String): Call<Land>

    @GET("/api/listings")
    fun getLands(@Header("Authorization")token: String): Call<MutableList<Land>>



}