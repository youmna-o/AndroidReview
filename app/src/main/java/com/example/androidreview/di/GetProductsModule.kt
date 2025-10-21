package com.example.androidreview.di

import com.example.androidreview.data.data_sources.ProductsRemoteDataSource
import com.example.androidreview.data.data_sources.ProductsRemoteDataSourceImpl
import com.example.androidreview.data.mappers.ProductMapper
import com.example.androidreview.data.repositories.ProductRepositoryImpl
import com.example.androidreview.domain.repositories.ProductRepository
import com.example.androidreview.domain.useCases.GetProductByIDUseCaseRX
import com.example.androidreview.domain.useCases.GetProductsUseCase
import org.koin.dsl.module
import org.koin.dsl.single

val getProductModule = module {

//    single {
//        ProductService(get())
//    }

    single<ProductsRemoteDataSource>{
        ProductsRemoteDataSourceImpl(get())
    }
    single <ProductRepository>{
        ProductRepositoryImpl(
            get(),
            get()
        )
    }

    single {
        ProductMapper()
    }
    single {
        GetProductsUseCase(get())
    }
    single {
        GetProductByIDUseCaseRX(get())
    }

}