package com.balakumar.weatherapplication.data

import com.balakumar.weatherapplication.models.weatherReport
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/v1/forecast")
    suspend fun getReport(
        @Query("latitude") lat :Double,
        @Query("longitude") log :Double,
        @Query("current") current:String ="temperature_2m,relative_humidity_2m,apparent_temperature,rain,wind_speed_10m,wind_direction_10m",
        @Query("hourly") hourly:String ="temperature_2m,relative_humidity_2m,rain,wind_speed_10m,wind_direction_10m,temperature_80m,soil_temperature_0cm,soil_moisture_0_to_1cm"
    ):Response<weatherReport>

}