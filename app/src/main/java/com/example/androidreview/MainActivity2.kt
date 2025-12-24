package com.example.androidreview

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.reactivex.rxjava3.core.Notification
import kotlinx.coroutines.channels.Channel

class MainActivity2 : ComponentActivity() {

    val Channel_ID = "channel_01"
    val Channel_Name = "My  First Channel"
    val Notification_ID = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("22", "onCreate: ")
        super.onCreate(savedInstanceState)
        sendNotification()
        val myIntent = Intent(this, MainActivity::class.java)

        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent =PendingIntent.getActivity(
            this,
            0,
            myIntent,
            pendingIntentFlags
        )
        val notification = NotificationCompat.Builder(this, Channel_ID)
            .setContentTitle("Title " +
                    "Hey How are you?")
            .setContentText("Message")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)


        setContent{
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
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
                    Button(
                        onClick = {
                           notificationManager.notify(Notification_ID,notification)
                            }

                    ) {
                        Text("send Notification")
                    }
                }

            }
        }


        }

    fun sendNotification(){

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
            // create Notification Channel
            val channel = NotificationChannel(
                Channel_ID,
                Channel_Name,
                NotificationManager.IMPORTANCE_HIGH
            )
            // create Notification Manager
            val manager = getSystemService(NOTIFICATION_SERVICE)  as NotificationManager
            manager.createNotificationChannel(channel)
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
