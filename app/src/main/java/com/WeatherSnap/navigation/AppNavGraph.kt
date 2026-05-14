package com.WeatherSnap.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.WeatherSnap.persantation.screen.CameraScreen
import com.WeatherSnap.persantation.screen.CreateReportScreen
import com.WeatherSnap.persantation.screen.SavedReportsScreen
import com.WeatherSnap.persantation.screen.WeatherScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "weather"
    ) {
        composable("weather") { 
            WeatherScreen(navController = navController) 
        }
        composable("camera") { 
            CameraScreen(navController = navController) 
        }
        composable("create_report") { 
            CreateReportScreen(navController = navController) 
        }
        composable("saved_reports") { 
            SavedReportsScreen(navController = navController)
        }
    }
}
