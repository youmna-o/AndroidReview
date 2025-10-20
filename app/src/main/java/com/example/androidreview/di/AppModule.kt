package com.example.androidreview.di

import org.koin.dsl.module

val appModule = module {
    includes(
        networkModule,
        getProductModule,
        viewModelsModule

    )
}