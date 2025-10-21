package com.example.androidreview.presentation.homeScreen

import android.content.Context
import android.content.Intent
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.androidreview.DetailsActivity
import com.example.androidreview.domain.entities.ProductResponse


@Composable
fun ProductList(products: List<ProductResponse>) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
    ) {
        items(products) { product ->
            ProductCard(product , context)
        }
    }
}
@Composable
fun ProductCard(product: ProductResponse, context: Context) {
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
                Button(onClick = {
                    val intent = Intent(context, DetailsActivity::class.java)
                    intent.putExtra("itemId", product.id)
                    context.startActivity(intent)
                }) {
                    Text("Go to Details Screen")
                }
            }
        }
    }
}