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
import dev.ymuratov.core.ui.theme.AppTheme
import dev.ymuratov.feature.categories.navigation.ROUTE_CATEGORIES
import dev.ymuratov.feature.categories.navigation.categoriesNav
import dev.ymuratov.feature.categoryproducts.navigation.categoryProductsNav
import dev.ymuratov.feature.categoryproducts.presentation.CategoryProductsViewModel
import dev.ymuratov.feature.productdetail.navigation.productDetailNav

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = ROUTE_CATEGORIES) {
                        categoriesNav(onCategoryClick = { category ->
                            navController.navigate("categoryProducts/$category")
                        })
                        categoryProductsNav(onProductClick = { id ->
                            navController.navigate("productDetail/$id")
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                CategoryProductsViewModel.RESULT_KEY,
                                false
                            )
                        })
                        productDetailNav(navController)
                    }
                }
            }
        }
    }
}