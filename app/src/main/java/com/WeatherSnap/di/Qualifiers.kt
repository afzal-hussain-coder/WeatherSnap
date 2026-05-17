package com.weathersnap.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GeoCodingApi


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WeatherApiQualifier