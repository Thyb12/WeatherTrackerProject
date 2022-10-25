package com.example.weathertrackerproject.domain.model

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
    constructor() : this(0, arrayOf(), arrayOf(), 0, 0, "", 0, 0, 0, 0)

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




    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Current

        if (_temperature != other._temperature) return false
        if (!_weather_icons.contentEquals(other._weather_icons)) return false
        if (!_weather_descriptions.contentEquals(other._weather_descriptions)) return false
        if (_wind_speed != other._wind_speed) return false
        if (_wind_degree != other._wind_degree) return false
        if (_wind_dir != other._wind_dir) return false
        if (_pressure != other._pressure) return false
        if (_precip != other._precip) return false
        if (_humidity != other._humidity) return false
        if (_cloudCover != other._cloudCover) return false
        if (temperature != other.temperature) return false
        if (!weatherIcons.contentEquals(other.weatherIcons)) return false
        if (!weatherDescriptions.contentEquals(other.weatherDescriptions)) return false
        if (windSpeed != other.windSpeed) return false
        if (windDegree != other.windDegree) return false
        if (windDir != other.windDir) return false
        if (pressure != other.pressure) return false
        if (precip != other.precip) return false
        if (humidity != other.humidity) return false
        if (cloudCover != other.cloudCover) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _temperature
        result = 31 * result + _weather_icons.contentHashCode()
        result = 31 * result + _weather_descriptions.contentHashCode()
        result = 31 * result + _wind_speed
        result = 31 * result + _wind_degree
        result = 31 * result + _wind_dir.hashCode()
        result = 31 * result + _pressure
        result = 31 * result + _precip
        result = 31 * result + _humidity
        result = 31 * result + _cloudCover
        return result
    }
}
