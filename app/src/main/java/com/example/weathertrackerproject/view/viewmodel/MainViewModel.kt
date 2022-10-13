package com.example.weathertrackerproject.view.viewmodel

import android.R.attr.x
import android.app.Activity
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertrackerproject.model.WeatherData
import com.example.weathertrackerproject.service.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _weatherInfo = MutableLiveData<WeatherData>()
    val weatherInfo: LiveData<WeatherData> = _weatherInfo

    private val _image = MutableLiveData<BitmapDrawable>()
    val image: LiveData<BitmapDrawable> = _image

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
                    val result = repository.updateWeatherData()
                    if (result.isSuccess){
                        _weatherInfo.value = result.getOrNull()

                        //ICON URL to BITMAP
                        result.getOrNull()?.let {
                            if (it.current._weather_icons.isNotEmpty()){
                                val connection: HttpURLConnection =
                                    URL(it.current._weather_icons[0]).openConnection() as HttpURLConnection
                                connection.connect()
                                val input: InputStream = connection.inputStream
                                _image.value = BitmapDrawable(Resources.getSystem(),BitmapFactory.decodeStream(input))
                            }
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
