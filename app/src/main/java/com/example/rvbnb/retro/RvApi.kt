package com.example.rvbnb.retro

import com.example.rvbnb.model.AcceptResponse
import com.example.rvbnb.model.Land
import com.example.rvbnb.model.Reservations
import com.example.rvbnb.model.UserAccount
import retrofit2.Call
import retrofit2.http.*

interface RvApi {
    @POST("/api/auth/register")
    fun registerUser(@Body user: UserAccount): Call<Void>

    @POST("/api/auth/login")
    fun loginUser(@Body userAccount: UserAccount): Call<AcceptResponse>

    @POST(" /api/listings")
    fun registerLand(@Header("Authorization")token: String,
                     @Body land: Land): Call<Void>

    @GET("/api/listings")
    fun getLandList(@Header("Authorization")token: String): Call<MutableList<Land>>

    @GET("/api/listings/{id}")
    fun getReservations(@Header("Authorization")token: String,
                        @Path("id")id: Int): Call<Reservations>

    @PUT("/api/listings/{id}")
    fun updateLand(@Header("Authorization")token: String,
                   @Path("id")id: Int,
                   @Body land: Land): Call<Void>

    @DELETE("/api/listings/{id}")
    fun deleteLandListingById(@Header("Authorization")token: String,
                              @Path("id")id: String): Call<Void>
}