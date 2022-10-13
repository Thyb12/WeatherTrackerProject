package com.example.weathertrackerproject.view.ui

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Load Information
        // weatherViewModel.loadData()

        //refresh Button
        binding.update.setOnClickListener {
           viewModel.loadData(this)
        }

        //Update Information
        val info = Observer<WeatherData> { newWeatherInfo ->

            binding.weatherIcon.setImageDrawable(viewModel.image.value)
            binding.name.text = newWeatherInfo.location?.name ?: ""
            binding.description.text = newWeatherInfo.current.weatherDescriptions[0]
            binding.temperature.text = newWeatherInfo.current.temperature.toString()

            binding.windDir.text = newWeatherInfo.current.windDir
            binding.windspeed.text = newWeatherInfo.current.windSpeed.toString()
            binding.winddegree.text = newWeatherInfo.current.windDegree.toString()

            binding.precip.text = newWeatherInfo.current.precip.toString()
            binding.humidity.text = newWeatherInfo.current.humidity.toString()



        }
        viewModel.weatherInfo.observe(this,info)
    }
}