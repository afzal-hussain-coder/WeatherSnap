package com.weathersnap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.weathersnap.navigation.AppNavGraph
import com.weathersnap.ui.theme.WeatherSnapTheme
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher =

        registerForActivityResult(

            ActivityResultContracts.RequestPermission()

        ) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        requestPermissionLauncher.launch(
            Manifest.permission.CAMERA
        )

        setContent {
            WeatherSnapTheme {
                AppNavGraph()
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun WeatherSnapPreview() {
    WeatherSnapTheme {
        AppNavGraph()
    }
}


