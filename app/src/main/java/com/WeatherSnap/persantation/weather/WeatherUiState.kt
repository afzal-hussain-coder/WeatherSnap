package com.weathersnap.presentation.weather

import com.weathersnap.domain.model.City
import com.weathersnap.domain.model.CurrentWeather

data class WeatherUiState(
    val loading: Boolean = false,
    val suggestionLoading: Boolean = false,
    val cities: List<City> = emptyList(),
    val weather: CurrentWeather? = null,
    val selectedCity: City? = null,
    val error: String? = null,
    val searchQuery: String = ""
)