package dev.ymuratov.feature.categories.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.ymuratov.core.navigation.AppDestination
import dev.ymuratov.feature.categories.presentation.CategoriesScreen

fun NavGraphBuilder.categoriesGraph(onCategoryClick: (String) -> Unit) {
    composable<AppDestination.Categories> {
        CategoriesScreen(onCategoryClick = onCategoryClick)
    }
}


