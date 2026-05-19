package com.weathersnap.domain.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeather(
    @SerializedName("temperature_2m")
    val temperature: Double,
    @SerializedName("relative_humidity_2m")
    val humidity: Int,
    @SerializedName("pressure_msl")
    val pressure: Double,
    @SerializedName("wind_speed_10m")
    val windSpeed: Double,
    @SerializedName("weather_code")
    val weatherCode: Int

) : Parcelable