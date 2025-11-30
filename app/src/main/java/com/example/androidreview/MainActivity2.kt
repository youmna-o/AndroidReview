package com.example.androidreview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("22", "onCreate: ")

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        Intent(this@MainActivity2, MainActivity::class.java).also {
                            startActivity(it)
                        }

                        // or startActivity(Intent(this@MainActivity2, MainActivity::class.java))
                    }
                ) {
                    Text("tab to start")
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.i("22", "onStart: ")    }

    override fun onResume() {
        super.onResume()
        Log.i("22", "onResume: ")
    }
    override fun onPause() {
        super.onPause()
        Log.i("22", "onPause: ")    }

    override fun onStop() {
        super.onStop()
        Log.i("22", "onStop: ")    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("22", "onDestroy: ")    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.i("22", "onWindowFocusChanged: ")
    }

    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        
        super.onTopResumedActivityChanged(isTopResumedActivity)
        Log.i("22", "onTopResumedActivityChanged: ")
    }
}
