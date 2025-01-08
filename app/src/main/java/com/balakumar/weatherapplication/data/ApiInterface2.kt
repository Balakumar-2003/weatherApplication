package com.balakumar.weatherapplication.data

import com.balakumar.weatherapplication.models.CountryLatLog
import com.balakumar.weatherapplication.models.CountryLatLogItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface2 {

    @GET("/v1/geocoding")
    suspend fun getLatLog(
        @Query("city")city :String,
        @Query("X-Api-Key") apiKey :String
    ):Response<CountryLatLog>
}