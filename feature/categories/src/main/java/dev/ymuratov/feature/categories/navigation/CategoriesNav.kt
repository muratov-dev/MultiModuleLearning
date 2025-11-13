package dev.ymuratov.feature.categories.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.ymuratov.feature.categories.presentation.CategoriesRoute

const val ROUTE_CATEGORIES = "categories"

fun NavGraphBuilder.categoriesNav(onCategoryClick: (String) -> Unit) {
    composable(route = ROUTE_CATEGORIES) {
        CategoriesRoute(onCategoryClick = onCategoryClick)
    }
}


