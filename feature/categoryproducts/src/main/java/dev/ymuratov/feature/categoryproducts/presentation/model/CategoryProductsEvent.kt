package dev.ymuratov.feature.categoryproducts.presentation.model

sealed interface CategoryProductsEvent {

    data object OnDataRefresh : CategoryProductsEvent
    data object OnNavigateUp : CategoryProductsEvent
    data class OnProductSelect(val productId: Int): CategoryProductsEvent
}