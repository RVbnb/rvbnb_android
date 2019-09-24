package com.example.rvbnb.retro

import com.example.rvbnb.model.Land
import com.example.rvbnb.model.LandOwner
import com.example.rvbnb.model.RvOwner
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RvApi {
    @POST
    fun registerLandOwner(@Body landOwner: LandOwner): Call<LandOwner>

    @POST
    fun registerRvOwner(@Body rvOwner: RvOwner): Call<RvOwner>

    @POST
    fun registerLand(@Body land: Land): Call<Land>

}