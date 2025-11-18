package dev.ymuratov.mm_learning.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.ymuratov.core.navigation.AppDestination
import dev.ymuratov.core.ui.utils.defaultModifier
import dev.ymuratov.feature.categories.presentation.navigation.categoriesGraph
import dev.ymuratov.feature.categoryproducts.presentation.navigation.categoryProductsGraph
import dev.ymuratov.feature.productdetail.presentation.navigation.productDetailGraph

@Composable
fun AppNavHost(navController: NavHostController, startDestination: AppDestination, modifier: Modifier = Modifier) {
    val screenModifier = Modifier.defaultModifier()
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        categoriesGraph(modifier = screenModifier) { slug, title ->
            navController.navigate(AppDestination.CategoryProducts(slug, title))
        }
        categoryProductsGraph(
            modifier = screenModifier,
            navigateToProductDetails = { navController.navigate(AppDestination.ProductDetail(it)) },
            navigateUp = { navController.navigateUp() },
        )
        productDetailGraph(modifier = screenModifier) {
            navController.navigateUp()
        }
    }
}