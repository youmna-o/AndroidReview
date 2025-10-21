package com.example.androidreview.data.network

import com.example.androidreview.data.models.ProductResponseDTO
import com.example.androidreview.data.models.ProductsResponseDTO
import io.reactivex.rxjava3.core.Single
import okio.Sink
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getAllProducts(
        @Url url: String,
    ) : Response<ProductsResponseDTO>

    //put url static here because we cant ue path and url
    @GET("products/{id}")
     fun getProductByIDRX(
        @Path("id") id: Int
    ) : Single<Response<ProductResponseDTO>>
}