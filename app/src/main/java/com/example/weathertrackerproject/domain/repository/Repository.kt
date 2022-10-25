package com.example.weathertrackerproject.domain.repository

import com.example.weathertrackerproject.domain.model.WeatherData


interface Repository {
    suspend fun updateWeatherData(): Result<WeatherData>
}