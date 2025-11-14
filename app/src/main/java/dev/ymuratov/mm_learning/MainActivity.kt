package dev.ymuratov.mm_learning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.ymuratov.core.navigation.AppDestination
import dev.ymuratov.core.ui.theme.AppTheme
import dev.ymuratov.feature.categories.presentation.navigation.categoriesGraph
import dev.ymuratov.feature.categoryproducts.presentation.navigation.categoryProductsNav
import dev.ymuratov.feature.productdetail.presentation.navigation.productDetailNav

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = AppDestination.Categories) {
                        categoriesGraph {
                            navController.navigate(AppDestination.CategoryProducts(it))
                        }
                        categoryProductsNav {
                            navController.navigate(AppDestination.ProductDetail(it))
                        }
                        productDetailNav(navController)
                    }
                }
            }
        }
    }
}