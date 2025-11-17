package dev.ymuratov.feature.productdetail.presentation.model

sealed interface ProductDetailAction {

    data object NavigateUp : ProductDetailAction
    data object NavigateToHome : ProductDetailAction
}