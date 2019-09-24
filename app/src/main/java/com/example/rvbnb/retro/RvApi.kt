package com.example.rvbnb.retro

import com.example.rvbnb.model.AcceptResponse
import com.example.rvbnb.model.Land
import com.example.rvbnb.model.UserAccount
import retrofit2.Call
import retrofit2.http.*

interface RvApi {
    @POST("/api/auth/register")
    fun registerUser(@Body user: UserAccount): Call<Void>

    @POST("/api/auth/login")
    fun loginUser(@Body user: UserAccount): Call<AcceptResponse>

    @POST(" /api/listings")
    fun registerLand(@Body land: Land,
                     @Header("Authorization")token: String): Call<Void>

    @GET("/api/listings")
    fun getLandList(@Header("Authorization")token: String): Call<MutableList<Land>>

    @PUT("/api/listings/{id}")
    fun updateLand(@Body land: Land): Call<Void>

    @DELETE("/api/listings/{id}")
    fun deleteLandListingById(@Path("id")id: String): Call<Void>
}