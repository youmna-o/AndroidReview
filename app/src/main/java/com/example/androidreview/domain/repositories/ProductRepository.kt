package com.example.androidreview.domain.repositories

import com.example.androidreview.domain.entities.ProductResponse
import io.reactivex.rxjava3.core.Single

interface ProductRepository {
    suspend fun getAllProducts(): Result<List<ProductResponse>>
     fun getProductIDRX(id:Int): Result<Single<ProductResponse>>

}