package com.weathersnap.di

import com.weathersnap.data.remote.CityApi
import com.weathersnap.data.remote.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import com.weathersnap.data.local.AppDatabase
import com.weathersnap.data.local.dao.ReportDao

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build() }


    // =========================
    // GEOCODING RETROFIT
    // =========================

    @GeoCodingApi
    @Provides
    @Singleton
    fun provideGeoRetrofit(client: OkHttpClient): Retrofit {

        return Retrofit.Builder()

            .baseUrl(
                "https://geocoding-api.open-meteo.com/"
            )
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }


    // =========================
    // WEATHER RETROFIT
    // =========================

    @WeatherApiQualifier
    @Provides
    @Singleton
    fun provideWeatherRetrofit(
        client: OkHttpClient

    ): Retrofit {

        return Retrofit.Builder()

            .baseUrl(
                "https://api.open-meteo.com/"
            )

            .client(client)

            .addConverterFactory(
                GsonConverterFactory.create()
            )

            .build()
    }


    // =========================
    // CITY API
    // =========================

    @Provides
    @Singleton
    fun provideCityApi(

        @GeoCodingApi retrofit:
        Retrofit

    ): CityApi {

        return retrofit.create(
            CityApi::class.java
        )
    }


    // =========================
    // WEATHER API
    // =========================

    @Provides
    @Singleton
    fun provideWeatherApi(

        @WeatherApiQualifier retrofit:
        Retrofit

    ): WeatherApi {

        return retrofit.create(
            WeatherApi::class.java
        )
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {

        return Room.databaseBuilder(

            context,

            AppDatabase::class.java,

            "weather_snap_db"

        ).build()
    }

    @Provides
    @Singleton
    fun provideReportDao(

        database: AppDatabase

    ): ReportDao {

        return database.reportDao()
    }
}