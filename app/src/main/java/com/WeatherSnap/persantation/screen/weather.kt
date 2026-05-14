package com.WeatherSnap.persantation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dagger.Component

@Composable
fun WeatherScreen(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Weather Screen")
    }

}