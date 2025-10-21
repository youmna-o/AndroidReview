package com.example.androidreview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidreview.domain.entities.ProductResponse
import com.example.androidreview.domain.useCases.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(private val getProductsUseCase: GetProductsUseCase): ViewModel() {
    //must specific the type of StateFlow
    private val mutableProductListState :MutableStateFlow<ResponseState<List<ProductResponse>>> = MutableStateFlow(ResponseState.Loading())
    val productListState = mutableProductListState.asStateFlow()

    private val mutableProductState :MutableStateFlow<ResponseState<ProductResponse?>> = MutableStateFlow(ResponseState.Loading())
    val productState = mutableProductState.asStateFlow()

    fun getAllProducts(){
        viewModelScope.launch {
            getProductsUseCase.invoke().onSuccess {
                mutableProductListState.value = ResponseState.Success(it)
            }.onFailure {
                mutableProductListState.value = ResponseState.Error(it)
            }
        }
    }

    fun getProductsById(id: Int?){
        viewModelScope.launch {
            getProductsUseCase.invoke()
                .onSuccess {
                mutableProductState.value = ResponseState.Success(data = it.firstOrNull{
                    it.id==id
                })
                }
                .onFailure {
                mutableProductListState.value = ResponseState.Error(it)
            }
        }
    }

}
//generic class to handle loading , success and error states
sealed class ResponseState<out T>{
    class Loading<T> : ResponseState<T>()
    data class Success<T>(val data: T) : ResponseState<T>()
    data class Error<T>(val error: Throwable) : ResponseState<T>()

}