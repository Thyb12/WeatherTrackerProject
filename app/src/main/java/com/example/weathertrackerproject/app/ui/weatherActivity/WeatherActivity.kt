package com.example.weathertrackerproject.app.ui.weatherActivity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.weathertrackerproject.app.ui.MainActivity
import com.example.weathertrackerproject.app.viewmodel.MainEvent
import com.example.weathertrackerproject.app.viewmodel.MainViewModel
import com.example.weathertrackerproject.R
import com.example.weathertrackerproject.app.viewmodel.WeatherState
import com.example.weathertrackerproject.domain.model.Current
import com.example.weathertrackerproject.domain.model.WeatherData

@Composable
fun WeatherScreen(
    activity: MainActivity = MainActivity(),
    viewModel: MainViewModel = hiltViewModel()
) {
    val weatherInfo = viewModel.state.value
    val textColor = MaterialTheme.colorScheme.secondary

    Box(modifier = Modifier.fillMaxSize()) {

        //TOP START CITY INFORMATION
        Column(
            modifier = Modifier
                .padding(all = 8.dp)
                .align(Alignment.TopStart)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = (weatherInfo.weatherInfo?.location?.name
                    ?: weatherInfo.weatherInfo?.location?.region ?: ""),
                color = textColor,
                style = MaterialTheme.typography.titleMedium
            )
        }

        //Center ICON INFO
        Image(
            painter = rememberAsyncImagePainter(weatherInfo.image),
            contentDescription = "weather Image",
            modifier = Modifier
                .clip(CircleShape)
                .align(Alignment.Center)
                .size(125.dp),
        )

        //Bottom
        //LEFT INFORMATION
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 60.dp),
            verticalArrangement = Arrangement.Top
        ) {
            BottomTemperatureInformation(
                weatherInfo = weatherInfo,
                textColor = textColor
            )
        }

        //RIGHT INFORMATION
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 90.dp)
        ) {
            BottomWindInformation(
                weatherInfo = weatherInfo,
                textColor = textColor
            )
        }

        //RESET WEATHER INFORMATION BUTTON
        Button(
            onClick = {
                viewModel.onEvent(MainEvent.Button(activity))
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
        ) {
            Text(
                text = "Actualisation",
                color = textColor
            )
        }
    }
}

@Composable
fun BottomTemperatureInformation(weatherInfo: WeatherState, textColor: Color) {
    if (!weatherInfo.weatherInfo?.current?.weatherDescriptions?.get(0).isNullOrBlank()) {
        Text(
            text = weatherInfo.weatherInfo?.current?.weatherDescriptions?.get(0) ?: "",
            color = textColor,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = weatherInfo.weatherInfo?.current?.temperature.toString() + " °",
            color = textColor,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun BottomWindInformation(weatherInfo: WeatherState, textColor: Color) {

    //WIND Information
    if (!weatherInfo.weatherInfo?.current?.windDir.isNullOrBlank()) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(color = MaterialTheme.colorScheme.tertiary)
                .padding(start = 5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "",
                colorFilter = ColorFilter.tint(textColor),
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = weatherInfo.weatherInfo?.current?.windDir.toString(),
                color = textColor
            )
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = weatherInfo.weatherInfo?.current?.windSpeed.toString() + " km/h",
                color = textColor
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        //INFORMATION precipitation and humidity
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "précipitation : " + weatherInfo.weatherInfo?.current?.precip.toString() + " %",
            color = textColor
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "humiditée : " + weatherInfo.weatherInfo?.current?.humidity.toString() + " %",
            color = textColor
        )
    }
}


@Preview
@Composable
fun BottomTemperaturePreview() {
    BottomTemperatureInformation(
        weatherInfo = WeatherState(
            WeatherData(
                current = Current().copy(
                    _temperature = 10,
                    _weather_descriptions = arrayOf("Sunrise")
                )
            )
        ),
        textColor = MaterialTheme.colorScheme.secondary
    )
}

@Preview
@Composable
fun BottomWindPreview() {
    BottomWindInformation(
        weatherInfo = WeatherState(),
        textColor = MaterialTheme.colorScheme.secondary
    )
}

@Preview
@Composable
fun MainActivityPreview() {
    WeatherScreen()
}