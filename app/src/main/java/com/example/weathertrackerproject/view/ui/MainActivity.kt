package com.example.weathertrackerproject.view.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.weathertrackerproject.databinding.ActivityMainBinding
import com.example.weathertrackerproject.model.WeatherData
import com.example.weathertrackerproject.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Load Information
        viewModel.loadData(this)

        //refresh Button
        binding.update.setOnClickListener {
            viewModel.loadData(this)
        }

        //Update Information
        val info = Observer<WeatherData> { newWeatherInfo ->

            binding.weatherIcon.setImageDrawable(viewModel.image.value)
            binding.name.text = newWeatherInfo.location?.name ?: ""
            binding.description.text = newWeatherInfo.current.weatherDescriptions[0]
            binding.temperature.text = newWeatherInfo.current.temperature.toString() + " degré"

            binding.windDir.text = newWeatherInfo.current.windDir
            binding.windspeed.text = newWeatherInfo.current.windSpeed.toString() + " km/h"
            binding.winddegree.text = newWeatherInfo.current.windDegree.toString() + " degré"

            binding.precip.text = newWeatherInfo.current.precip.toString() + " %"
            binding.humidity.text = newWeatherInfo.current.humidity.toString() + " %"


        }
        viewModel.weatherInfo.observe(this, info)
    }
}