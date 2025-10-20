package com.example.androidreview.data.network

import com.example.androidreview.data.models.ProductsResponseDTO

interface ApiHelper {
    suspend fun getAllProducts(): ProductsResponseDTO

}