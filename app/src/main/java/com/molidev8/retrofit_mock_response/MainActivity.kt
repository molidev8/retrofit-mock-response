package com.molidev8.retrofit_mock_response

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.molidev8.retrofit_mock_response.retrofit.MockApi
import com.molidev8.retrofit_mock_response.ui.theme.RetrofitmockresponseTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RetrofitmockresponseTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var response: List<String> by remember {
                        mutableStateOf(List(1) { "No Articles" })
                    }
                    Greeting("I have ${response.size} articles and the first one is: \n ${response.first()}")

                    Button(
                        modifier = Modifier.size(200.dp, 200.dp).padding(20.dp),
                        onClick = {
                            response = getArticles()
                        }) {
                        Text(text = "Get Articles!")
                    }
                }
            }
        }
    }
}

private fun getArticles(): List<String> = runBlocking {
    return@runBlocking withContext(Dispatchers.IO) {
        return@withContext MockApi.retrofitService.getArticles()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitmockresponseTheme {
        val response = List(1) { "No Articles" }
        Greeting("I have ${response.size} articles and the first one is: \n ${response.first()}")
    }
}