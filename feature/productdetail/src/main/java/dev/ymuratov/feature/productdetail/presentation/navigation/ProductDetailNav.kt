package dev.ymuratov.feature.productdetail.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.ymuratov.core.navigation.AppDestination
import dev.ymuratov.feature.productdetail.presentation.screen.ProductDetailContainer

fun NavGraphBuilder.productDetailGraph(
    modifier: Modifier = Modifier, navigateUp: () -> Unit = {}
) {
    composable<AppDestination.ProductDetail> {
        ProductDetailContainer(modifier = modifier, navigateUp = navigateUp)
    }
}


