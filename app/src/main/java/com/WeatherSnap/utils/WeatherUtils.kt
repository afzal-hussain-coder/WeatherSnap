package com.weathersnap.utils

fun getWeatherCondition(code: Int): String {

    return when (code) {
        0 -> "Clear Sky"
        1, 2, 3 ->
            "Partly Cloudy"
        45, 48 ->
            "Fog"
        51, 53, 55 ->
            "Drizzle"
        61, 63, 65 ->
            "Rain"
        71, 73, 75 ->
            "Snow"
        95 ->
            "Thunderstorm"
        else ->
            "Unknown"
    }
}

fun Long.toReadableSize(): String {
    val kb = this / 1024.0
    val mb = kb / 1024.0

    return when {
        mb >= 1.0 -> String.format("%.2f MB", mb)   // 2.25 MB
        kb >= 1.0 -> String.format("%.0f KB", kb)   // 2305 KB
        else -> "${this} Bytes"
    }
}