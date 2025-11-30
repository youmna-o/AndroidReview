package com.example.androidreview

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.padding

import androidx.compose.ui.Modifier

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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.GlobalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("yomna", "onCreate: ")
        enableEdgeToEdge()
        setContent {
            AndroidReviewTheme {
                val productsViewModel: ProductsViewModel = koinViewModel()
                val navController = rememberNavController()

                Observable.just("Apple", "Banana", "Orange")
                    .map { fruit -> fruit.uppercase() } // transform each item
                    .filter {
                        it.startsWith("A")
                    }
                    .subscribeOn(Schedulers.io()) // run on background thread
                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe(
                        { item ->
                            Log.i("TAG", "=========onCreate:  $item")
                        },   // onNext
                        { error ->
                            Log.i("TAG", "==================error")
                        },    // onError
                        { println("Done!") }                      // onComplete
                    )
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

    override fun onStart() {
        super.onStart()
        Log.i("yomna", "onStart: ")    }

    override fun onResume() {
        super.onResume()
        Log.i("yomna", "onResume: ")
    }
    override fun onPause() {
        super.onPause()
        Log.i("yomna", "onPause: ")    }

    override fun onStop() {
        super.onStop()
        Log.i("yomna", "onStop: ")    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("yomna", "onDestroy: ")    }
}




