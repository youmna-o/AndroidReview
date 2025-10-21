package com.example.androidreview.data.data_sources

import com.example.androidreview.data.models.ProductResponseDTO
import io.reactivex.rxjava3.core.Single

interface ProductsRemoteDataSource {
    suspend fun getAllProducts(): List<ProductResponseDTO>
    fun getProductByIDRX(id:Int): Single<ProductResponseDTO>

}