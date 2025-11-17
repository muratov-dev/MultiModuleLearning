package dev.ymuratov.mm_learning

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.ymuratov.core.navigation.AppDestination
import dev.ymuratov.core.ui.theme.AppTheme
import dev.ymuratov.core.ui.utils.defaultModifier
import dev.ymuratov.feature.categories.presentation.navigation.categoriesGraph
import dev.ymuratov.feature.categoryproducts.presentation.navigation.categoryProductsGraph
import dev.ymuratov.feature.productdetail.presentation.navigation.productDetailGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        )
        setContent {
            AppTheme {
                val navController = rememberNavController()
                val screenModifier = Modifier.defaultModifier()
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = AppDestination.Categories) {
                        categoriesGraph(modifier = screenModifier) {
                            navController.navigate(AppDestination.CategoryProducts(it))
                        }
                        categoryProductsGraph(modifier = screenModifier) {
                            navController.navigate(AppDestination.ProductDetail(it))
                        }
                        productDetailGraph(modifier = screenModifier) {
                            navController.navigateUp()
                        }
                    }
                }
            }
        }
    }
}