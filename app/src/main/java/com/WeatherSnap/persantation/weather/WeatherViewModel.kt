package com.weathersnap.presentation.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weathersnap.data.repository.WeatherRepository
import com.weathersnap.domain.model.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {


    private val _uiState = MutableStateFlow(WeatherUiState())

    val uiState: StateFlow<WeatherUiState> =
        _uiState.asStateFlow()


    // =========================
    // SEARCH CITY
    // =========================

    fun searchCity(
        query: String
    ) {

        if (query.length <= 2) {

            return
        }

        viewModelScope.launch {

            try {

                _uiState.value =
                    _uiState.value.copy(

                        suggestionLoading  = true,

                        error = null
                    )

                val cities =
                    repository.searchCities(
                        query
                    )

                _uiState.value =
                    _uiState.value.copy(

                        suggestionLoading  = false,

                        cities = cities
                    )

            } catch (e: Exception) {

                _uiState.value =
                    _uiState.value.copy(

                        suggestionLoading  = false,

                        error = e.message
                    )
            }
        }
    }


    // =========================
    // FETCH WEATHER
    // =========================

    fun fetchWeather(
        city: City
    ) {

        viewModelScope.launch {

            // SHOW SHIMMER
            _uiState.value = _uiState.value.copy(

                loading = true,

                weather = null,

                selectedCity = city,

                error = null
            )

            try {

                val response =
                    repository.getWeather(

                        city.latitude,

                        city.longitude
                    )

                // SHOW WEATHER
                _uiState.value = _uiState.value.copy(

                    loading = false,

                    weather = response.current,

                    cities = emptyList()
                )

            } catch (e: Exception) {

                // SHOW ERROR
                _uiState.value = _uiState.value.copy(

                    loading = false,

                    weather = null,

                    error = e.message
                )
            }
        }
    }

    fun selectCity(
        city: City
    ) {

        _uiState.value =
            _uiState.value.copy(

                searchQuery = city.name,

                selectedCity = city,

                cities = emptyList()
            )
    }

}