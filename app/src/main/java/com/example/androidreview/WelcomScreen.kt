package com.example.androidreview

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun WelcomeScreen(navController: NavController){

    val job = GlobalScope.launch {
        delay(10_000) // 10 seconds
        Log.i("WelcomeScreen", "startttt")
    }


    runBlocking {
        delay(3000)  // 3 seconds
        job.cancel()
        Log.i("WelcomeScreen", "finishhh: ")

    }

    Column (
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Text("weclommmmmmmmmmm")
        Button(onClick = { navController.navigate("Home") }) {
            Text("Go to home")
        }
    }

}