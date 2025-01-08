package com.balakumar.weatherapplication.models

data class weatherReport(
    val current: Current,
    val current_units: CurrentUnits,
    val elevation: Int,
    val generationtime_ms: Double,
    val hourly: Hourly,
    val hourly_units: HourlyUnits,
    val latitude: Int,
    val longitude: Int,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)