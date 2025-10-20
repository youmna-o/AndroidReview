package com.example.androidreview.data.repositories

import com.example.androidreview.data.data_sources.ProductsRemoteDataSource
import com.example.androidreview.data.mappers.ProductMapper
import com.example.androidreview.data.models.ProductResponseDTO
import com.example.androidreview.domain.entities.ProductResponse
import com.example.androidreview.domain.repositories.ProductRepository


class ProductRepositoryImpl(
    private  val productsRemoteDataSource: ProductsRemoteDataSource,
    private val productMapper: ProductMapper)
: ProductRepository {
    override suspend fun getAllProducts(): Result<List<ProductResponse>> {
        return try {
            val responseDTO = productsRemoteDataSource.getAllProducts()
            val response = productMapper.mapResponseDtoToResponse(responseDTO)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}