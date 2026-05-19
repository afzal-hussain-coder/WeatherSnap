package com.weathersnap.presentation.weather

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.weathersnap.presentation.components.*
import com.weathersnap.ui.theme.*
import com.weathersnap.utils.getWeatherCondition
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()

) {
    val state by viewModel.uiState.collectAsState()
    var searchText by remember {
        mutableStateOf("")
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .background(

                brush = Brush.verticalGradient(
                    colors = listOf(
                        weatherScreenBackgroundStart,
                        weatherScreenBackgroundEnd
                    )
                )
            )
            .padding(8.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(40.dp))
            // HEADER
            AppHeader(
                onReportsClick = {
                    navController.navigate(
                        "saved_reports"
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            // SEARCH CARD
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF11150F)
                )
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            WeatherSearchField(
                                value = searchText,
                                onValueChange = {
                                    searchText = it
                                    if (it.length > 2) {
                                        viewModel.searchCity(it)
                                    }
                                })
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(
                            onClick = {
                                if (searchText.length > 2) {
                                    viewModel.searchCity(
                                        searchText
                                    )
                                }
                            },
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ButtonColor
                            )
                        ) {
                            Text(
                                text = "Search",
                                fontSize = 8.sp,
                                color = DarkText
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "Enter more than 2 letters to start city suggestions.",
                        color = LightText,
                        fontSize = 8.sp
                    )
                }
            }


            Spacer(modifier = Modifier.height(10.dp))

            // SUGGESTION LIST

            AnimatedVisibility(

                visible = state.cities.isNotEmpty()
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = CardColor)
                ) {
                    Column {
                        state.cities.forEach { city ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                                    .clickable {
                                        viewModel.fetchWeather(
                                            city
                                        )
                                    },
                                shape = RoundedCornerShape(50.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor =
                                        Color.Transparent
                                ),

                                border = BorderStroke(
                                    width = 1.dp,
                                    color =
                                        BorderColor.copy(
                                            alpha = 0.5f
                                        )
                                )
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            vertical = 5.dp
                                        ),
                                    contentAlignment =
                                        Alignment.Center
                                ) {
                                    Text(
                                        text =
                                            "${city.name}, ${city.country}",
                                        color = WhiteText,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }
                    }
                }

            }


// LOADING
            AnimatedVisibility(

                visible = state.loading
            ) {

                Column {

                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )

                    Card(

                        modifier = Modifier.fillMaxWidth(),

                        shape = RoundedCornerShape(20.dp),

                        colors = CardDefaults.cardColors(

                            containerColor =
                                CardColor
                        )
                    ) {

                        Row(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),

                            horizontalArrangement =
                                Arrangement.spacedBy(16.dp),

                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            CircularProgressIndicator(
                                color = ButtonColor
                            )

                            Text(
                                text = "Loading weather...",
                                color = ButtonColor,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

// EMPTY STATE
            AnimatedVisibility(
                visible = !state.loading && state.weather == null
            ) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = CardColor)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            TempBoxColor,
                                            humidityTextColor
                                        )
                                    )
                                )
                                .padding(0.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Search,Capture,Save",
                                color = LightText,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal

                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "No Weather loaded",
                            color = WhiteText,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            lineHeight = 8.sp

                        )
                        Text(
                            text = "Enter more than 2 letters, choose a city; then search",
                            color = WhiteText,
                            fontWeight = FontWeight.Normal,
                            fontSize = 8.sp,
                            lineHeight = 8.sp
                        )
                    }
                }
            }


// WEATHER CARD
            AnimatedVisibility(

                visible = state.weather != null
            ) {

                state.weather?.let { weather ->

                    Card(

                        shape = RoundedCornerShape(20.dp),

                        colors = CardDefaults.cardColors(

                            containerColor = CardColor
                        )
                    ) {

                        Column(

                            modifier =
                                Modifier.padding(10.dp)
                        ) {

                            Row(

                                modifier = Modifier.fillMaxWidth(),

                                horizontalArrangement =
                                    Arrangement.SpaceBetween
                            ) {

                                Column {

                                    Text(

                                        text =
                                            state.selectedCity?.name
                                                ?: "",

                                        color = WhiteText,

                                        fontWeight =
                                            FontWeight.Bold,

                                        fontSize = 22.sp
                                    )

                                    Text(

                                        text =
                                            getWeatherCondition(
                                                weather.weatherCode
                                            ),

                                        color = LightText,

                                        fontSize = 12.sp
                                    )
                                }

                                Box(

                                    modifier = Modifier
                                        .clip(
                                            RoundedCornerShape(10.dp)
                                        )
                                        .background(
                                            TempBoxColor
                                        )
                                        .padding(

                                            horizontal = 10.dp,

                                            vertical = 6.dp
                                        )
                                ) {

                                    Text(

                                        text =
                                            "${weather.temperature}°C",

                                        color = WhiteText,

                                        fontWeight =
                                            FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(
                                modifier =
                                    Modifier.height(10.dp)
                            )

                            Row(

                                modifier = Modifier.fillMaxWidth(),

                                horizontalArrangement =
                                    Arrangement.SpaceBetween
                            ) {

                                WeatherInfoCard(

                                    title = "Humidity",

                                    value =
                                        "${weather.humidity}%",

                                    humadityBoxColor,

                                    humidityTextColor
                                )

                                WeatherInfoCard(

                                    title = "Wind",

                                    value =
                                        "${weather.windSpeed} m/s",

                                    backgroundColor =
                                        windBoxColor,

                                    windTextColor
                                )

                                WeatherInfoCard(

                                    title = "Pressure",

                                    value =
                                        "${weather.pressure}",

                                    backgroundColor =
                                        pressureBoxColor,

                                    pressureTextColor
                                )
                            }

                            Spacer(
                                modifier =
                                    Modifier.height(14.dp)
                            )

                            Card(

                                shape =
                                    RoundedCornerShape(10.dp),

                                colors =
                                    CardDefaults.cardColors(

                                        containerColor =
                                            reportBoxColor
                                    )
                            ) {

                                Row(

                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),

                                    horizontalArrangement =
                                        Arrangement.SpaceBetween
                                ) {

                                    Text(

                                        text =
                                            "Report readiness",

                                        color = LightText,

                                        fontSize = 8.sp
                                    )

                                    Text(

                                        text =
                                            "Camera and Room DB enabled",

                                        color = WhiteText,

                                        fontSize = 8.sp,

                                        fontWeight =
                                            FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(
                                modifier =
                                    Modifier.height(16.dp)
                            )


                            // CREATE REPORT BUTTON

                            PrimaryButton(

                                text = "Create Report",

                                onClick = {

                                    navController
                                        .currentBackStackEntry
                                        ?.savedStateHandle
                                        ?.set(

                                            "weather_data",

                                            weather
                                        )

                                    navController
                                        .currentBackStackEntry
                                        ?.savedStateHandle
                                        ?.set(

                                            "city_name",

                                            state.selectedCity?.name
                                                ?: ""
                                        )

                                    navController.navigate(
                                        "create_report"
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }


}