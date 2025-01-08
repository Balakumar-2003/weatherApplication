package com.balakumar.weatherapplication.models

data class CurrentUnits(
    val apparent_temperature: String,
    val interval: String,
    val rain: String,
    val relative_humidity_2m: String,
    val temperature_2m: String,
    val time: String,
    val wind_direction_10m: String,
    val wind_speed_10m: String
)