package com.balakumar.weatherapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.balakumar.weatherapplication.screens.WeatherHome

import com.balakumar.weatherapplication.ui.theme.WeatherApplicationTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    lateinit var viewmodel:WeatherAppViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherApplicationTheme {
                viewmodel=viewModel()
                WeatherHome(viewModel = viewmodel)
            }
        }
    }
}

