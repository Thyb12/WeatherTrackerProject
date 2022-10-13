package com.example.weathertrackerproject.service.repository

import com.example.weathertrackerproject.model.WeatherData


interface Repository {
    suspend fun updateWeatherData(): Result<WeatherData>
}