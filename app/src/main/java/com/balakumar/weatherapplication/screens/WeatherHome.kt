package com.balakumar.weatherapplication.screens


import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.balakumar.weatherapplication.WeatherAppViewModel
import com.balakumar.weatherapplication.ui.theme.WeatherApplicationTheme
import  androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.Log
import com.balakumar.weatherapplication.data.NetworkResponse
import kotlinx.coroutines.launch


@Composable
fun WeatherHome(viewModel: WeatherAppViewModel,width:Float,height:Float) {
    val scope = rememberCoroutineScope()
    val weather by viewModel.weatherReport.observeAsState()
    val focus = LocalFocusManager.current
    val context = LocalContext.current

    var searchText by remember {
        mutableStateOf("Chennai")
    }
    Surface (modifier=Modifier.fillMaxSize(), color = Color.Black){
    Box (modifier = Modifier.fillMaxSize()){
        Canvas(modifier = Modifier.fillMaxSize()) {
            val drawpath = Path().apply {
                moveTo(width, 450f) // Move to top center of the canvas
                lineTo(0f, 650f) // Draw line to bottom-right corner
                lineTo(width, 650f)
                close()
            }
            drawPath(
                path = drawpath,
                color = Color(0xff4B6A2F), // Triangle color
                style = Fill // Fill the triangle
            )
            val drawPath2 = Path().apply {
                moveTo(width, 650f)
                lineTo(0f, height - 200f)
                lineTo(1100f, height - 300f)
            }
            drawPath(
                path = drawPath2,
                color = Color(0xFF4B6A2F),
                style = Fill
            )
        }
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top){
            Surface(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 12.dp, end = 12.dp),
                shape = RoundedCornerShape(30),
                color = Color(0xFF3C3434)
            ) {
                TextField(value = searchText, modifier = Modifier.fillMaxWidth(),
                    onValueChange ={searchText=it},
                    placeholder = { Text(text = "Search Location", color = Color.White)}
                    , textStyle = MaterialTheme.typography.bodySmall,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focus.clearFocus()
                        scope.launch {
                            viewModel.getLatLog(searchText)
                        }}
                        Log.d("")
                    ),
                    singleLine = true, maxLines = 1,
                    colors = TextFieldDefaults
                        .colors(focusedContainerColor = Color(0xFF3C3434),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                        unfocusedContainerColor = Color(0xFF3C3434),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent, cursorColor = Color.White)
                )


            }
            when (val result =weather){
                is NetworkResponse.error ->{
                    Toast.makeText(context,result.msg,Toast.LENGTH_SHORT).show()
                }
                NetworkResponse.loading ->{
                    CircularProgressIndicator()
                }
                is NetworkResponse.success ->{
                    Row (modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        horizontalArrangement = Arrangement.Start){
                        Text(text = result.data.current.apparent_temperature.toString(),
                            fontSize = 24.sp,
                            style = MaterialTheme.typography.headlineLarge, color = Color.White
                        )
                        Text(text = result.data.current_units.apparent_temperature,
                            modifier = Modifier.padding(start = 4.dp),
                            fontSize = 24.sp,
                            style = MaterialTheme.typography.headlineLarge, color = Color.White)
                    }

                }
                else ->{}
            }
        }
        
    }
    }

}
//val context = LocalContext.current
//val scope = rememberCoroutineScope()
//val report by viewModel.weatherReport.observeAsState()
//val letlog by viewModel.country.observeAsState()
//var city by remember {
//    mutableStateOf("Chennai")
//}
//    Column(modifier= Modifier
//        .fillMaxSize()
//        .padding(top = 30.dp)
//        .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center){
//        OutlinedTextField(value = city, onValueChange ={
//            city=it} , modifier = Modifier
//            .fillMaxWidth()
//            .padding(4.dp), singleLine = true)
//        Button(onClick = {scope.launch {
//            viewModel.getLatLog(city)
//        }}) {
//            Text("click to get")
//        }
//        when(val result = report){
//            is NetworkResponse.error ->{
//                Toast.makeText(context,result.msg,Toast.LENGTH_SHORT).show()
//            }
//            NetworkResponse.loading ->{
//                CircularProgressIndicator()
//            }
//            is NetworkResponse.success ->{
//                Text(text = result.data.toString(), lineHeight = 24.sp, fontSize = 18.sp)
//            }
//
//            null -> {}
//        }
//        Text(letlog.toString(), fontSize = 18.sp, lineHeight = 24.sp)
//    }
@Preview(showBackground = true)
@Composable
fun PreviewWeatherHome(){
    WeatherApplicationTheme {
        WeatherHome(viewModel = viewModel<WeatherAppViewModel>(),0f,0f)
    }
}