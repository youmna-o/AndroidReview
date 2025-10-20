package com.example.androidreview.data.models

import com.google.gson.annotations.SerializedName

// Root response for products API

data class ProductsResponseDTO(
    @SerializedName("products") val products: List<ProductResponseDTO> = emptyList()
)

