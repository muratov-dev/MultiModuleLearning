package dev.ymuratov.feature.categoryproducts.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.ymuratov.feature.categoryproducts.presentation.CategoryProductsRoute

const val ARG_CATEGORY = "category"
const val ROUTE_CATEGORY_PRODUCTS = "categoryProducts/{$ARG_CATEGORY}"

fun NavGraphBuilder.categoryProductsNav(onProductClick: (Int) -> Unit) {
    composable(
        route = ROUTE_CATEGORY_PRODUCTS,
        arguments = listOf(navArgument(ARG_CATEGORY) { type = NavType.StringType })
    ) {
        CategoryProductsRoute(onProductClick = onProductClick)
    }
}


