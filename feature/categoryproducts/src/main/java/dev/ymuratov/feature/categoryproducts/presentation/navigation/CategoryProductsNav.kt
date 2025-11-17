package dev.ymuratov.feature.categoryproducts.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.ymuratov.core.navigation.AppDestination
import dev.ymuratov.feature.categoryproducts.presentation.screen.CategoryProductsContainer

fun NavGraphBuilder.categoryProductsGraph(
    modifier: Modifier = Modifier, navigateToProductDetails: (Int) -> Unit, navigateUp: () -> Unit
) {
    composable<AppDestination.CategoryProducts> {
        CategoryProductsContainer(
            modifier = modifier, navigateToProductDetails = navigateToProductDetails, navigateUp = navigateUp
        )
    }
}


