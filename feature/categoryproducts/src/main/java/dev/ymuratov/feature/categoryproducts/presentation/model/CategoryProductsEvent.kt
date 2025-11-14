package dev.ymuratov.feature.categoryproducts.presentation.model

sealed interface CategoryProductsEvent {

    data object OnDataRefresh : CategoryProductsEvent
    data class OnProductSelect(val productId: Int): CategoryProductsEvent
}