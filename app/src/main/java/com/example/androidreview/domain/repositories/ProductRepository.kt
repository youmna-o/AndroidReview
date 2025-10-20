package com.example.androidreview.domain.repositories

import com.example.androidreview.domain.entities.ProductResponse

interface ProductRepository {
    suspend fun getAllProducts(): Result<List<ProductResponse>>

}