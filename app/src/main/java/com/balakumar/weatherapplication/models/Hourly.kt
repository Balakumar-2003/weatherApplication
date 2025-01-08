package com.balakumar.weatherapplication.models

data class Hourly(
    val rain: List<Int>,
    val relative_humidity_2m: List<Int>,
    val soil_moisture_0_to_1cm: List<Double>,
    val soil_temperature_0cm: List<Double>,
    val temperature_2m: List<Double>,
    val temperature_80m: List<Double>,
    val time: List<String>,
    val wind_direction_10m: List<Int>,
    val wind_speed_10m: List<Double>
)