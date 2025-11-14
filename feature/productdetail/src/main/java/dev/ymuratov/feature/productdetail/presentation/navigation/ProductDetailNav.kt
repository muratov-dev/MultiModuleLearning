package dev.ymuratov.feature.productdetail.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.ymuratov.core.navigation.AppDestination
import dev.ymuratov.feature.productdetail.presentation.screen.ProductDetailContainer
import dev.ymuratov.feature.productdetail.presentation.viewmodel.ProductDetailViewModel

const val ARG_PRODUCT_ID = "id"
const val ROUTE_PRODUCT_DETAIL = "productDetail/{$ARG_PRODUCT_ID}"

fun NavGraphBuilder.productDetailNav(
    navController: NavController
) {
    composable<AppDestination.ProductDetail> {
        ProductDetailContainer(navigateUp = {
            navController.popBackStack()
        })
    }
}


