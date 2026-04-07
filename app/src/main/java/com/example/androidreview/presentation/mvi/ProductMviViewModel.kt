package com.example.androidreview.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidreview.domain.repositories.ProductRepository
import com.example.androidreview.domain.useCases.GetProductByIDUseCaseRX
import com.example.androidreview.domain.useCases.GetProductsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductMviViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductByIDUseCaseRX: GetProductByIDUseCaseRX
)  : ViewModel(){
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()
    private val _effect = MutableSharedFlow<ProductsEffect>()
    val effect = _effect.asSharedFlow()

    private var disposable: Disposable? = null

     fun onIntent(intent: ProductsIntent) {
        when (intent) {
            is ProductsIntent.FetchProducts -> {
                fetchProducts()
            }
            is ProductsIntent.OpenProductByID -> {
                viewModelScope.launch {
                    _effect.emit(ProductsEffect.NavigateToShowDetails(intent.id.toString()))            }

            }
        }
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            // 1. Loading
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            // 2. Call UseCase (same as before)
            val result = getProductsUseCase.invoke()

            // 3. Handle result
            result.onSuccess { products ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        products = products
                    )
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }
            }
        }
    }

    private fun openProductByID(id: Int) {

        // 1. Loading state
        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }

        val result = getProductByIDUseCaseRX.invoke(id)

        disposable = result.fold(
            onSuccess = { single ->
                single
            },
            onFailure = { error ->
                Single.error(error)
            }
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ product ->

                // 2. Success
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        selectedProduct = product
                    )
                }

            }, { error ->

                // 3. Error
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Error loading product"
                    )
                }
            })
    }
}