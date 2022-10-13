package com.example.weathertrackerproject.model

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("temperature")
    val _temperature: Int,
    @SerializedName("weather_icons")
    val _weather_icons: Array<String>,
    @SerializedName("weather_descriptions")
    val _weather_descriptions: Array<String>,
    @SerializedName("wind_speed")
    val _wind_speed: Int,
    @SerializedName("wind_degree")
    val _wind_degree: Int,
    @SerializedName("wind_dir")
    val _wind_dir: String,
    @SerializedName("pressure")
    val _pressure: Int,
    @SerializedName("precip")
    val _precip: Int,
    @SerializedName("humidity")
    val _humidity: Int,
    @SerializedName("cloudcover")
    val _cloudCover: Int
) {
    constructor() : this(0, arrayOf(),arrayOf(),0,0,"",0,0,0,0)

    val temperature get() = _temperature
    val weatherIcons get() = _weather_icons
    val weatherDescriptions get() = _weather_descriptions
    val windSpeed get() = _wind_speed
    val windDegree get() = _wind_degree
    val windDir get() = _wind_dir
    val pressure get() = _pressure
    val precip get() = _precip
    val humidity get() = _humidity
    val cloudCover get() = _cloudCover
}
