package dev.ymuratov.feature.categoryproducts.presentation.model

sealed interface CategoryProductsAction {

    data object NavigateUp : CategoryProductsAction
    data class NavigateToProductDetails(val productId: Int) : CategoryProductsAction
}