package com.example.androidreview.data.data_sources

import com.example.androidreview.data.models.ProductResponseDTO
import com.example.androidreview.data.network.ApiHelper

class ProductsRemoteDataSourceImpl(private val apiHelper: ApiHelper): ProductsRemoteDataSource {
    override suspend fun getAllProducts() : List<ProductResponseDTO> {
        return apiHelper.getAllProducts().products
    }
}