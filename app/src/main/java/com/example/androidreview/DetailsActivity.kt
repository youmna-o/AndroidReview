package com.example.androidreview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import coil.load
import com.example.androidreview.databinding.ActivityDetailsBinding
import com.example.androidreview.presentation.ProductsViewModel
import com.example.androidreview.presentation.ResponseState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val productsViewModel: ProductsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("itemId", -1)
        if (id != -1) {
            productsViewModel.getProductsById(id)
        }

        // Collect the state safely with lifecycle awareness
        lifecycleScope.launch {
//   Without repeatOnLifecycle, your collector:
//            keeps running even when the Activity is paused or destroyed ❌
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                productsViewModel.productState.collect { state ->
                    when (state) {
                        is ResponseState.Loading -> {
                            binding.title.text = "Loading..."
                        }

                        is ResponseState.Success -> {
                            val product = state.data
                            if (product != null) {
                                binding.title.text = product.title
                                binding.price.text = "${product.price} $"
                                binding.description.text = product.description
                                binding.image.load(product.image)
                            } else {
                                binding.title.text = "Product not found"
                            }
                        }

                        is ResponseState.Error -> {
                            binding.title.text = "Error: ${state.error.message}"
                        }
                    }
                }
                // }
            }
        }
    }
}

