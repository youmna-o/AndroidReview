package com.example.androidreview.presentation.mvi

import com.example.androidreview.domain.entities.ProductResponse

sealed interface ProductsIntent {
    data object FetchProducts : ProductsIntent
    data class OpenProductByID(val id: Int) : ProductsIntent
}

data class ProductUiState (
    val isLoading: Boolean = false,
    val  products : List<ProductResponse> = emptyList(),
    val selectedProduct: ProductResponse? = null,
    val  errorMessage: String? = null,
)
sealed class ProductsEffect {
    data class NavigateToShowDetails(val productId: String) : ProductsEffect()
}

