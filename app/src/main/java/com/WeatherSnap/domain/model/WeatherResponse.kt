package com.weathersnap.domain.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

    @SerializedName("current")
    val current: CurrentWeather
)