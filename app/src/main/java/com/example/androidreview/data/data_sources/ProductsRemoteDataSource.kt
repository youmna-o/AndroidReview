package com.example.androidreview.data.data_sources

import com.example.androidreview.data.models.ProductResponseDTO

interface ProductsRemoteDataSource {
    suspend fun getAllProducts(): List<ProductResponseDTO>

}