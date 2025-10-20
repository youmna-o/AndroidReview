package com.example.androidreview

import android.app.Application
import com.example.androidreview.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(listOf(appModule))
        }
        // Initialize any libraries or configurations here if needed
    }
}