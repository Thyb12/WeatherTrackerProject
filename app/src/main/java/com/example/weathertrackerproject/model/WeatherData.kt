package com.example.weathertrackerproject.model


data class WeatherData (
    val request: Request? = Request(),
    val location: Location? = Location(),
    val current: Current = Current()
)