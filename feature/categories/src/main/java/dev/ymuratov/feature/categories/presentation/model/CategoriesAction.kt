package dev.ymuratov.feature.categories.presentation.model

sealed interface CategoriesAction {

    data class NavigateToCategoryProducts(val categorySlug: String) : CategoriesAction
}