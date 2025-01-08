package com.balakumar.weatherapplication.screens

import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.balakumar.weatherapplication.WeatherAppViewModel
import com.balakumar.weatherapplication.data.NetworkResponse
import kotlinx.coroutines.launch

@Composable
fun WeatherHome(viewModel: WeatherAppViewModel){
    val context= LocalContext.current
    val scope= rememberCoroutineScope()
    val report by viewModel.weatherReport.observeAsState()
    var city by remember {
        mutableStateOf("Chennai")
    }
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        OutlinedTextField(value = city, onValueChange ={
            city=it} , modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp), singleLine = true)
        Button(onClick = {scope.launch {
            viewModel.getLatLog(city)
        }}) {
            Text("click to get")
        }
        when(val result = report){
            is NetworkResponse.error ->{
                Toast.makeText(context,result.msg,Toast.LENGTH_SHORT).show()
            }
            NetworkResponse.loading ->{
                CircularProgressIndicator()
            }
            is NetworkResponse.success ->{
                Text(text = result.data.toString(), lineHeight = 24.sp, fontSize = 18.sp)
            }

            null -> {}
        }
    }
}