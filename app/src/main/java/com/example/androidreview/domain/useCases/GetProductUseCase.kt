package com.example.androidreview.domain.useCases

import com.example.androidreview.domain.entities.ProductResponse
import com.example.androidreview.domain.repositories.ProductRepository


class GetProductsUseCase ( private  val productRepo: ProductRepository){
    suspend operator fun invoke(): Result<List<ProductResponse>>{
        return productRepo.getAllProducts()
    }


}
