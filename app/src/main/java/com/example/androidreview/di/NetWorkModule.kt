package com.example.androidreview.di

import com.example.androidreview.data.network.ApiHelper
import com.example.androidreview.data.network.ApiHelperImpl
import com.example.androidreview.data.network.ApiService
import com.example.androidreview.data.network.RetrofitGenerator
import org.koin.dsl.module

val networkModule = module {
    single<ApiService>{
        val retrofit = RetrofitGenerator.generateRetrofit()
        retrofit.create(ApiService::class.java)

    }
    single<ApiHelper> {
        ApiHelperImpl(get())
    }

}