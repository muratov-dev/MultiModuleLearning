package dev.ymuratov.feature.categories.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.ymuratov.core.navigation.AppDestination
import dev.ymuratov.feature.categories.presentation.screen.CategoriesContainer

fun NavGraphBuilder.categoriesGraph(
    modifier: Modifier = Modifier, navigateToCategoryProducts: (String, String) -> Unit
) {
    composable<AppDestination.Categories> {
        CategoriesContainer(modifier = modifier, navigateToCategoryProducts = navigateToCategoryProducts)
    }
}


