package com.weathersnap.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.weathersnap.persantation.camera.CustomCameraScreen
import com.weathersnap.persantation.report.SavedReportsScreen
import com.weathersnap.presentation.report.CreateReportScreen
import com.weathersnap.presentation.weather.WeatherScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "weather_screen"
    ) {
        composable("weather_screen") {
            WeatherScreen(navController = navController)
        }
        composable("camera") {
            CustomCameraScreen(navController = navController)
        }
        composable("create_report") {
            CreateReportScreen(navController = navController)
        }
        composable("saved_reports") {
            SavedReportsScreen(navController = navController)
        }
        composable(route = "camera_screen") {
            CustomCameraScreen(navController)
        }
        composable(route = "saved_reports") {
            SavedReportsScreen(navController)
        }
    }
}
