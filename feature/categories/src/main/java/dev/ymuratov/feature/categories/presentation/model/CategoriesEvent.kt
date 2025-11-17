package dev.ymuratov.feature.categories.presentation.model

sealed interface CategoriesEvent {

    data object OnDataRefresh: CategoriesEvent
    data class OnCategorySelect(val categorySlug: String, val categoryTitle: String): CategoriesEvent
}