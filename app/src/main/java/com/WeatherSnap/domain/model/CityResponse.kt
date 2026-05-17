package com.weathersnap.domain.model

import com.google.gson.annotations.SerializedName

data class CityResponse(

    @SerializedName("results")
    val results: List<City>?
)

data class City(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("country")
    val country: String,

    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val longitude: Double
)