package com.example.androidreview

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.androidreview.domain.entities.ProductResponse
import com.example.androidreview.presentation.ProductsViewModel
import com.example.androidreview.presentation.ResponseState
import com.example.androidreview.ui.theme.AndroidReviewTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.GlobalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidReviewTheme {
                val productsViewModel: ProductsViewModel = koinViewModel()
                Scaffold(modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        context = this,
                        productsViewModel = productsViewModel
                    )
                }
            }

        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, context: Context, productsViewModel: ProductsViewModel) {
    val productsState by productsViewModel.productListState.collectAsStateWithLifecycle()
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello $name!")

        Button(onClick = {
            productsViewModel.getAllProducts()
        }) {
            Text(text = "Click Me")
        }
        // Show product list below the button if loaded
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

@Composable
fun ProductCard(product: ProductResponse) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                product.title?.let { Text(text = it) }
                Text(text = "${product.price} EGP")
            }
        }
    }
}

@Composable
fun ProductList(products: List<ProductResponse>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(products) { product ->
            ProductCard(product)
        }
    }
}
