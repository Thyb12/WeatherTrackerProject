package com.example.weathertrackerproject.data.api

import com.google.gson.JsonElement
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("current")
    suspend fun updateWeatherData(
        @Query("access_key") key: String,
        @Query("query") latLon: String,
    ): JsonElement
}