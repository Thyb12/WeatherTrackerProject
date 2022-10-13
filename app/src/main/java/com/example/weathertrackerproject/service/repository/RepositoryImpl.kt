package com.example.weathertrackerproject.service.repository

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.annotation.RequiresPermission
import com.example.weathertrackerproject.model.WeatherData
import com.example.weathertrackerproject.service.api.Api
import com.google.gson.GsonBuilder
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RepositoryImpl(
    private val api: Api,
    private val appContext: Context
) : Repository {
    private val key = "afde3eded2325b5a91fd46dfec7cf676"
    private val locationManager =
        appContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    override suspend fun updateWeatherData(): Result<WeatherData> {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val location = suspendCoroutine<Location>
            {
                locationManager.getCurrentLocation(
                    LocationManager.NETWORK_PROVIDER, null, appContext.mainExecutor
                ) { location ->
                    it.resume(location)
                }
            }
            val latLon = location.latitude.toString() + "," + location.longitude
            val result = api.updateWeatherData(key = key, latLon = latLon)
            val gson = GsonBuilder().serializeNulls().create()
            val re = gson.fromJson(result, WeatherData::class.java)
            return Result.success(re)
        }
        return Result.failure(Throwable("no location"))
    }


}