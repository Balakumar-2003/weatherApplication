package com.balakumar.weatherapplication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balakumar.weatherapplication.data.NetworkResponse
import com.balakumar.weatherapplication.models.CountryLatLogItem
import com.balakumar.weatherapplication.models.weatherReport
import com.balakumar.weatherapplication.utils.Constant
import com.balakumar.weatherapplication.utils.RetrofitInstance
import com.balakumar.weatherapplication.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class WeatherAppViewModel:ViewModel() {
    private var _weatherReport = MutableLiveData<NetworkResponse<weatherReport>>()
     var weatherReport : LiveData<NetworkResponse<weatherReport>> = _weatherReport
    var country = MutableLiveData(CountryLatLogItem())
    var lat=13.0836939;
    var log=80.270186;
init {
    viewModelScope.launch {
        getLatLog("Chennai")

    }
}
    suspend fun getLatLog(city:String){
        viewModelScope.launch {
            val response=try {
                RetrofitInstance.api2.getLatLog(city,Constant.apiKey)
                        }
            catch (e:HttpException){
                return@launch
            }
            catch (e:IOException){
                return@launch
            }
            if(response.isSuccessful && response.body() != null){
                country.value= response.body()?.let { it.list.first() }
                lat= country.value!!.latitude
                lat= country.value!!.longitude
                getReport()
            }
        }
    }
    private suspend fun getReport(){
        viewModelScope.launch {
            val reponse=try {
                    RetrofitInstance.api.getReport(lat,log)
            }
            catch (e:HttpException){
                _weatherReport.value=NetworkResponse.error(e.message())
                return@launch
            }
            catch (e:IOException){
                _weatherReport.value=NetworkResponse.error(e.message?:"io exception")
                return@launch
            }
            if(reponse.isSuccessful && reponse.body() != null){
                _weatherReport.value = NetworkResponse.success(reponse.body()!!)
            }
        }
    }
}