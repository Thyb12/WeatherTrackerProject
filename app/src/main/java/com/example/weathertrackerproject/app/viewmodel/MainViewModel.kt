package com.example.weathertrackerproject.app.viewmodel

import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertrackerproject.domain.model.WeatherData
import com.example.weathertrackerproject.domain.UseCase.UpdateWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val updateWeatherDataUseCase: UpdateWeatherDataUseCase
) : ViewModel() {

    private val _state : MutableState<WeatherState> = mutableStateOf(WeatherState())
    val state: State<WeatherState> = _state

    fun onEvent(event: MainEvent) {
        when(event){
            is MainEvent.Button -> {
                loadData(event.activity)
            }
        }

    }




    fun loadData(activity: Activity) {
        viewModelScope.launch {
            try {
                if (ActivityCompat.checkSelfPermission(
                        activity,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    //Update Weather Information
                    val result = updateWeatherDataUseCase.updateWeatherData()
                    if (result.isSuccess) {

                        result.getOrNull()?.let {
                            _state.value = _state.value.copy(
                                weatherInfo = result.getOrNull(),
                                image = it.current._weather_icons[0]
                            )
                        }

                    } else {
                        //Information besoin du wifi / localisation

                    }

                } else {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(
                            android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.ACCESS_FINE_LOCATION
                        ),
                        1
                    )
                }
            } catch (e: java.lang.Exception) {
                // Retrofit error
                e.printStackTrace()
            }
        }
    }
}
data class WeatherState(
    val weatherInfo: WeatherData? = null,
    val image: String = ""
)

sealed class MainEvent {

    data class Button(val activity: Activity): MainEvent()
}
