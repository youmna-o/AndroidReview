package com.example.androidreview.data.network

import com.example.androidreview.data.models.ProductResponseDTO
import com.example.androidreview.data.models.ProductsResponseDTO
import io.reactivex.rxjava3.core.Single

interface ApiHelper {
    suspend fun getAllProducts(): ProductsResponseDTO
    fun getProductByIDRX(id: Int): Single<ProductResponseDTO>

}