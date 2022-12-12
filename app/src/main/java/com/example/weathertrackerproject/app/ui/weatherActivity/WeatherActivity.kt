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
    Column(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = rememberAsyncImagePainter(weatherInfo.image),
            contentDescription = "weather Image",
            modifier = Modifier
                // Set image size to 40 dp
                .size(70.dp),
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "ville : " + (weatherInfo.weatherInfo?.location?.name
                ?: weatherInfo.weatherInfo?.location?.region ?: ""),
            color = textColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = weatherInfo.weatherInfo?.current?.weatherDescriptions?.get(0) ?: "",
            color = textColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = weatherInfo.weatherInfo?.current?.temperature.toString() + " degrés",
            color = textColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

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

        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = "precipitation : " + weatherInfo.weatherInfo?.current?.precip.toString() + " %",
            color = textColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "humidity : " + weatherInfo.weatherInfo?.current?.humidity.toString() + " %",
            color = textColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.onEvent(MainEvent.Button(activity))
            },
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