package com.balakumar.weatherapplication.models

data class HourlyUnits(
    val rain: String,
    val relative_humidity_2m: String,
    val soil_moisture_0_to_1cm: String,
    val soil_temperature_0cm: String,
    val temperature_2m: String,
    val temperature_80m: String,
    val time: String,
    val wind_direction_10m: String,
    val wind_speed_10m: String
)