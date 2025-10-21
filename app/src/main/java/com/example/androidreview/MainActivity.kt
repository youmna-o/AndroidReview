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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.androidreview.domain.entities.ProductResponse
import com.example.androidreview.presentation.ProductsViewModel
import com.example.androidreview.presentation.ResponseState
import com.example.androidreview.presentation.homeScreen.HomeScreen
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
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "Welcome"
                ) {
                    composable("Welcome") { WelcomeScreen(navController) }
                    composable("Home") {
                        HomeScreen(
                            navController,
                            name = "Android",
                            modifier = Modifier.padding(6.dp),
                            productsViewModel = productsViewModel
                        )
                    }
//                    composable("details/{itemId}",
//                        arguments = listOf(navArgument("itemId") { type = NavType.IntType })
//                    ) { backStackEntry ->
//                        val itemId = backStackEntry.arguments?.getInt("itemId")
//                        DetailsScreen(navController, itemId)
//                    }
                }


            }
        }
    }
}







