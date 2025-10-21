package com.example.androidreview.presentation.homeScreen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.androidreview.DetailsActivity
import com.example.androidreview.domain.entities.ProductResponse
import com.example.androidreview.presentation.ProductsViewModel
import com.example.androidreview.presentation.ResponseState

@Composable
fun HomeScreen(navController: NavController,name: String, modifier: Modifier = Modifier, productsViewModel: ProductsViewModel) {
    //Flow itself → not lifecycle-aware
    //When collected using repeatOnLifecycle or collectAsStateWithLifecycle → it becomes lifecycle-aware
    val productsState by productsViewModel.productListState.collectAsStateWithLifecycle()
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        productsViewModel.getAllProducts()
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween // Adjusts spacing between children
    ) {
        Box(modifier = Modifier.weight(1f, fill = true)) {
            when(productsState){
                is ResponseState.Loading -> {
                    Text(text = "Loading products...")
                }
                is ResponseState.Success -> {
                    val products = (productsState as ResponseState.Success<List<ProductResponse>>).data
                    ProductList(products = products)
                }
                is ResponseState.Error -> {
                    val error = (productsState as ResponseState.Error).error
                    Text(text = "Error loading products: ${error.message}")
                }
            }
        }

    }
}
