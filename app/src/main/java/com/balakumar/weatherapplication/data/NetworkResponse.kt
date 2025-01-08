package com.balakumar.weatherapplication.data

sealed class NetworkResponse<out T> {
    data class success<out T>(val data:T):NetworkResponse<T>()
    data class error(val msg:String):NetworkResponse<Nothing>()
    object loading : NetworkResponse<Nothing>()
}