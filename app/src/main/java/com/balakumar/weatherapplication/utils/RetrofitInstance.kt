package com.balakumar.weatherapplication.utils

import com.balakumar.weatherapplication.data.ApiInterface
import com.balakumar.weatherapplication.data.ApiInterface2
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api :ApiInterface by lazy {
        Retrofit
            .Builder()
            .baseUrl(Utils.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
    val api2 :ApiInterface2 by lazy{
        Retrofit
            .Builder()
            .baseUrl(Utils.baseUrl2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface2::class.java)
    }
}