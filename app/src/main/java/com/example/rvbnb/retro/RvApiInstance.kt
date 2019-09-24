package com.example.rvbnb.retro

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RvApiInstance {
    const val BASE_URL = "https://rvbnb.herokuapp.com"

    fun createRvApi(): RvApi{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(RvApi::class.java)
    }
}