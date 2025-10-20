package com.example.androidreview.data.network

import com.example.androidreview.data.models.ProductsResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getAllProducts(
        @Url url: String,
    ) : Response<ProductsResponseDTO>
}