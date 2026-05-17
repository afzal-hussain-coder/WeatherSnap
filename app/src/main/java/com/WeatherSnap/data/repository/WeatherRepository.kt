package com.weathersnap.data.repository

import com.weathersnap.data.remote.CityApi
import com.weathersnap.data.remote.WeatherApi
import com.weathersnap.domain.model.City
import com.weathersnap.domain.model.WeatherResponse
import javax.inject.Inject
import com.weathersnap.data.local.dao.ReportDao
import com.weathersnap.data.local.entity.ReportEntity

class WeatherRepository @Inject constructor(
    private val cityApi: CityApi,
    private val weatherApi: WeatherApi,
    private val reportDao: ReportDao
) {

    // Cache
    private val cityCache =
        mutableMapOf<String, List<City>>()

    // SEARCH CITY

    suspend fun searchCities(
        query: String
    ): List<City> {

        cityCache[query]?.let {

            return it
        }

        val response =
            cityApi.searchCity(query)

        val cities =
            response.results ?: emptyList()

        cityCache[query] = cities

        return cities
    }

    // GET WEATHER
    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherResponse {
        return weatherApi.getWeather(
            latitude,
            longitude
        )
    }
    suspend fun saveReport(report: ReportEntity) {
        reportDao.insertReport(report)
    }

    fun getReports() = reportDao.getReports()
}