package com.balakumar.weatherapplication.models

data class Current(
    val apparent_temperature: Double,
    val interval: Int,
    val rain: Double,
    val relative_humidity_2m: Int,
    val temperature_2m: Double,
    val time: String,
    val wind_direction_10m: Int,
    val wind_speed_10m: Double
)