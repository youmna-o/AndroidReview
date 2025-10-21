package com.example.androidreview.domain.useCases

import com.example.androidreview.domain.entities.ProductResponse
import com.example.androidreview.domain.repositories.ProductRepository
import io.reactivex.rxjava3.core.Single


class GetProductByIDUseCaseRX ( private  val productRepo: ProductRepository){
    fun invoke(id:Int): Result<Single<ProductResponse>>{
        return productRepo.getProductIDRX(id)
    }


}