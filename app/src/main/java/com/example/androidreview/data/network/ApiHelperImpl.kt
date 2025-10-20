package com.example.androidreview.data.network

import com.example.androidreview.data.models.ProductsResponseDTO
import com.example.androidreview.utils.AppConstants
import retrofit2.Response

/*Parameter vs Property:

Without val/var: apiService is just a constructor parameter, accessible only during initialization
With private val: apiService becomes a class property that can be used throughout all class methods

Without val/var: Parameter is not stored after constructor completes
With private val: Object reference is stored as a property for the lifetime of the class

 */

class ApiHelperImpl (private val apiService: ApiService): ApiHelper{
    override suspend fun getAllProducts(): ProductsResponseDTO {
        val baseUrl = AppConstants.BASE_URL
        val response : Response<ProductsResponseDTO> = apiService.getAllProducts("products")
        if(response.isSuccessful){
            return response.body() ?: throw Exception("Response body is null")
        }else{
            throw Exception("API call failed with response code: ${response.code()}")
        }
    }
}