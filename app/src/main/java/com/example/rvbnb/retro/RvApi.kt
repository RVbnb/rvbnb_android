package com.example.rvbnb.retro

import com.example.rvbnb.model.*
import retrofit2.Call
import retrofit2.http.*

interface RvApi {
    @POST("/api/auth/register")
    fun registerUser(@Body user: UserAccount): Call<Void>

    @POST("/api/auth/login")
    fun loginUser(@Body userAccount: UserAccount): Call<AcceptResponse>

    @GET("/api/listings")
    fun getLandList(@Header("Authorization")token: String): Call<MutableList<Land>>

    @GET("/api/listings/{id}")
    fun getLandById(@Header("Authorization")token: String,
                              @Path("id")id: String): Call<Void>

    @POST(" /api/listings")
    fun addLand(@Header("Authorization")token: String,
                     @Body land: Land): Call<Void>


    @PUT("/api/listings/{id}")
    fun updateLand(@Header("Authorization")token: String,
                   @Path("id")id: Int,
                   @Body land: Land): Call<Void>

    @DELETE("/api/listings/{id}")
    fun deleteLandListingById(@Header("Authorization")token: String,
                              @Path("id")id: String): Call<Void>

    @GET("/api/listings/all/reservations")
    fun getAllReservations(@Header("Authorization")token: String): Call<Reservation>

    @GET("/api/listings/{id}/reservations")
    fun getReservationsById(@Header("Authorization")token: String,
                        @Path("id")id: Int): Call<MutableList<Reservation>>

    @POST("/api/listings/{id}/reservations")
    fun postReservation(@Header("Authorization")token: String,
                        @Path("id")id: Int,
                        @Body reservation: Reservation): Call<Void>

    @DELETE("/api/listings/reservations/{id}")
    fun deleteReservation(@Header("Authorization")token: String,
                          @Path("id")id: Int): Call<Void>

    @PUT("api/listings/{listing_id}/reservations/{id}")
    fun updateReservation(@Header("Authorization")token: String,
                          @Path("listing_id")listing_id: Int,
                          @Path("id")reservationId: Int,
                          @Body reservation: Reservation): Call<Void>
}