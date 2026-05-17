package com.weathersnap.data.remote

import com.weathersnap.domain.model.CityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {

    @GET("v1/search")
    suspend fun searchCity(

        @Query("name")
        query: String

    ): CityResponse
}