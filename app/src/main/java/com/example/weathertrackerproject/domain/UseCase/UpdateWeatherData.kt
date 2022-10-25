package com.example.weathertrackerproject.domain.UseCase

import com.example.weathertrackerproject.domain.repository.Repository
import com.example.weathertrackerproject.domain.model.WeatherData

class UpdateWeatherData (
    private val repository: Repository
) {
    suspend operator fun invoke(): Result<WeatherData> {
        return repository.updateWeatherData()
    }
}