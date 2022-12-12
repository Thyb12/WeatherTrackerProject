package com.example.weathertrackerproject.app.ui.weatherActivity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                style = MaterialTheme.typography.titleLarge
            )
        }

        //Center ICON INFO
        Image(
            painter = rememberAsyncImagePainter(weatherInfo.image),
            contentDescription = "weather Image",
            modifier = Modifier
                .align(Alignment.Center)
                .size(70.dp),
        )

        //Bottom


        //LEFT INFORMATION
        //INFORMATION Weather Description and Temperature
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 10.dp, bottom = 50.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = weatherInfo.weatherInfo?.current?.weatherDescriptions?.get(0) ?: "",
                color = textColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = weatherInfo.weatherInfo?.current?.temperature.toString() + " °",
                color = textColor,
                style = MaterialTheme.typography.titleLarge
            )
        }

        //RIGHT INFORMATION
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 10.dp, bottom = 50.dp)
        ) {

            //WIND Information
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.wind),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(textColor),
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = weatherInfo.weatherInfo?.current?.windDir.toString(),
                    color = textColor
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = weatherInfo.weatherInfo?.current?.windSpeed.toString() + " km/h",
                    color = textColor
                )
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
                text = "RESET",
                color = textColor
            )
        }
    }
}

@Preview
@Composable
fun MainActivityView() {
    WeatherScreen()
}