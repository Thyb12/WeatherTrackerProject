package com.example.weathertrackerproject.app.ui


import android.os.Bundle
import androidx.compose.material3.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import com.example.weathertrackerproject.app.ui.weatherActivity.WeatherScreen
import com.example.weathertrackerproject.app.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadData(this)
        setContent {
            ComposeTheme() {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ){
                    WeatherScreen(activity = this)
                }
            }
        }
    }
}