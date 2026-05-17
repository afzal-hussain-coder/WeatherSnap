package com.weathersnap.presentation.report

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.weathersnap.data.local.entity.ReportEntity
import com.weathersnap.domain.model.CurrentWeather
import com.weathersnap.persantation.report.ReportViewModel
import com.weathersnap.presentation.components.CreateReportScreenAppHeader
import com.weathersnap.presentation.components.PrimaryButton
import com.weathersnap.presentation.components.WeatherInfoCard
import com.weathersnap.ui.theme.*
import com.weathersnap.utils.getWeatherCondition
import java.io.File
import androidx.compose.ui.platform.LocalContext
import com.weathersnap.utils.compressImage

@Composable
fun CreateReportScreen(
    navController: NavController,
    viewModel: ReportViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val notes = viewModel.notes.collectAsState()

    // WEATHER DATA
    val weather = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<CurrentWeather>("weather_data")

    val cityName = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<String>("city_name")

    // IMAGE URI
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // REACTIVELY RECEIVE CAPTURED IMAGE
    val capturedImageState = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow<String?>("captured_image", null)
        ?.collectAsState()

    LaunchedEffect(capturedImageState?.value) {
        capturedImageState?.value?.let { uriString ->
            imageUri = Uri.parse(uriString)
        }
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
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // HEADER
            CreateReportScreenAppHeader(onReportsClick = {
                navController.navigate("weather_screen")
            })

            Spacer(modifier = Modifier.height(15.dp))

            // WEATHER CARD
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = CardColor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = cityName ?: "Unknown City",
                                color = WhiteText,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                lineHeight = 12.sp
                            )
                            Text(
                                text = getWeatherCondition(weather?.weatherCode ?: 0),
                                color = LightText,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 12.sp
                            )
                        }
                        Text(
                            text = "${weather?.temperature ?: 0}°C",
                            color = HeaderStart,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        WeatherInfoCard(
                            title = "Humidity",
                            value = "${weather?.humidity ?: 0}%",
                            backgroundColor = humadityBoxColor,
                            textColors = humidityTextColor
                        )
                        WeatherInfoCard(
                            title = "Wind",
                            value = "${weather?.windSpeed ?: 0} km/h",
                            backgroundColor = windBoxColor,
                            textColors = windTextColor
                        )
                        WeatherInfoCard(
                            title = "Pressure",
                            value = "${weather?.pressure ?: 0}",
                            backgroundColor = pressureBoxColor,
                            textColors = pressureTextColor
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            // IMAGE PREVIEW
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = CardColor)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        SmallCardColor,
                                        TempBoxColor
                                    )
                                )
                            )
                            .padding(0.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (imageUri != null) {
                            Image(
                                painter = rememberAsyncImagePainter(imageUri),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Text(
                                text = "Photo Preview",
                                color = LightText,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal

                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    // CAPTURE BUTTON
                    PrimaryButton(
                        text = "Capture Photo",
                        onClick = {
                            navController.navigate("camera_screen")
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            // NOTES

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = CardColor)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Field Notes",
                        color = WhiteText,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = notes.value,
                        onValueChange = { viewModel.updateNotes(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp),
                        textStyle = LocalTextStyle.current.copy(
                            color = WhiteText,
                            fontSize = 10.sp,
                            lineHeight = 12.sp
                        ),
                        placeholder = {
                            Text(
                                text = "Notes",
                                color = LightText,
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Normal
                            )

                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BorderColor,
                            unfocusedBorderColor = BorderColor,
                            focusedContainerColor = CardColor,
                            unfocusedContainerColor = CardColor
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))

            // SAVE BUTTON
            PrimaryButton(

                text = "Save Report",

                onClick = {

                    // ORIGINAL IMAGE
                    val imageFile =
                        imageUri?.path?.let {

                            File(it)
                        }


                    // COMPRESSED IMAGE
                    val compressedFile =
                        imageUri?.let {

                            compressImage(
                                context,
                                it
                            )
                        }


                    // REPORT OBJECT
                    val report =
                        ReportEntity(

                            cityName =
                                cityName ?: "",

                            temperature =
                                weather?.temperature ?: 0.0,

                            condition =
                                getWeatherCondition(
                                    weather?.weatherCode ?: 0
                                ),

                            humidity =
                                weather?.humidity ?: 0,

                            windSpeed =
                                weather?.windSpeed ?: 0.0,

                            pressure =
                                weather?.pressure ?: 0.0,

                            notes =
                                notes.value,

                            imagePath =
                                imageUri?.toString() ?: "",

                            originalImageSize =
                                imageFile?.length() ?: 0L,

                            compressedImageSize =
                                compressedFile?.length() ?: 0L,

                            timestamp =
                                System.currentTimeMillis()
                        )


                    // SAVE
                    viewModel.saveReport(

                        report = report,

                        onSuccess = {

                            navController.navigate(
                                "saved_reports"
                            )
                        }
                    )
                }
            )
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}


