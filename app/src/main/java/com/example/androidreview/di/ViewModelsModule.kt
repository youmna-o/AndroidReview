package com.example.androidreview.di

import com.example.androidreview.data.network.ApiHelper
import com.example.androidreview.data.network.ApiHelperImpl
import com.example.androidreview.data.network.ApiService
import com.example.androidreview.data.network.RetrofitGenerator
import com.example.androidreview.presentation.ProductsViewModel
import org.koin.dsl.module

val viewModelsModule = module {
    single<ProductsViewModel>{
        ProductsViewModel(get())
    }


}